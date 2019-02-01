package com.spheremall.core.resources.elasticSearch;

import com.spheremall.core.entities.Entity;
import com.spheremall.core.entities.Response;
import com.spheremall.core.entities.products.Product;
import com.spheremall.core.entities.products.ProductAttributeValue;
import com.spheremall.core.exceptions.SphereMallException;
import com.spheremall.core.filters.elasticsearch.ESSearchFilter;
import com.spheremall.core.filters.elasticsearch.common.ElasticSearchFilter;
import com.spheremall.core.filters.elasticsearch.compound.BoolFilter;
import com.spheremall.core.filters.elasticsearch.criterions.SortFilter;
import com.spheremall.core.filters.elasticsearch.criterions.TermsFilterCriteria;
import com.spheremall.core.filters.elasticsearch.facets.ESAttributesFilterCriteria;
import com.spheremall.core.filters.elasticsearch.facets.ESCatalogFilter;
import com.spheremall.core.filters.elasticsearch.facets.ESCatalogFilterImpl;
import com.spheremall.core.filters.elasticsearch.facets.configs.ESAttributesConfig;
import com.spheremall.core.filters.elasticsearch.facets.configs.ESBrandsConfig;
import com.spheremall.core.filters.elasticsearch.facets.configs.ESFactorValuesConfig;
import com.spheremall.core.filters.elasticsearch.facets.configs.ESFunctionalNamesConfig;
import com.spheremall.core.filters.elasticsearch.facets.configs.ESPriceRangeConfig;
import com.spheremall.core.filters.elasticsearch.facets.models.ESFacets;
import com.spheremall.core.filters.elasticsearch.terms.TermsFilter;
import com.spheremall.core.resources.SetUpResourceTest;

import org.bouncycastle.jcajce.provider.symmetric.DES;
import org.json.JSONException;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ElasticSearchResourceTest extends SetUpResourceTest {

    @Test
    public void testSetCompoundFilter() throws IOException, SphereMallException {

        TermsFilter termsFilter = new TermsFilter(new TermsFilterCriteria("variantsCompound", "fd4e12b4-df24-8882-d068-b73d26949e1f"));

        BoolFilter boolFilter = new BoolFilter();
        boolFilter.filter(termsFilter);

        ESSearchFilter filter = new ESSearchFilter();
        filter.index("sm-products", "sm-documents")
                .query(boolFilter);

        Response<List<Entity>> entities = client.elasticSearch()
                .filters(filter.asFilter())
                .fetch();

        Assert.assertNotNull(entities);

        int count = Integer.valueOf(entities.meta().get("count").toString());
        int tmp = 0;
        for (Entity entity : entities.data()) {
            tmp++;
            Product product = (Product) entity;
            for (ProductAttributeValue attr : product.productAttributeValues) {
                if (attr.attributes.get(0).code.equals("colorbas")) {
                    System.out.println("found");
                }
            }
        }
        Assert.assertEquals(count, tmp);
    }

    @Test
    public void testSearch() throws IOException, SphereMallException {
        Response<List<Entity>> entities = client.elasticSearch()
                .limit(2)
                .offset(0)
                .search("milner");

        Assert.assertNotNull(entities);
        Assert.assertEquals(entities.data().size(), 2);
    }

    @Test
    public void testGetFacets() throws IOException, SphereMallException, JSONException {
        ESCatalogFilterImpl catalogFilter = new ESCatalogFilterImpl(Arrays.asList(
                new ESPriceRangeConfig(),
                new ESBrandsConfig(),
                new ESAttributesConfig(Collections.singletonList("reward")),
                new ESFunctionalNamesConfig(),
                new ESFactorValuesConfig(Arrays.asList(1, 3))
        ));

        List<String> entities = new ArrayList<>();
        entities.add("sm-products");

        Response<ESFacets> facets = client.elasticSearch().facets(catalogFilter, "", entities);
        Assert.assertNotNull(facets);
    }

    @Test
    public void testElasticSearchFilterData() throws IOException, SphereMallException {

        ESCatalogFilter filter = new ESCatalogFilterImpl(Arrays.asList(
                new ESPriceRangeConfig(),
                new ESBrandsConfig(),
                new ESAttributesConfig(Collections.singletonList("reward")),
                new ESFunctionalNamesConfig(),
                new ESFactorValuesConfig(Arrays.asList(1, 3))
        ));

        filter.add(new ESAttributesFilterCriteria("reward", "0"));

        TermsFilterCriteria isMain = new TermsFilterCriteria("isMain", "1");
        TermsFilter isMainFilter = new TermsFilter(isMain);

        TermsFilterCriteria channel = new TermsFilterCriteria("channelIds", "5");
        TermsFilter channelFilter = new TermsFilter(channel);

        BoolFilter boolFilter = filter.toBoolFilter();
        boolFilter.must(isMainFilter, channelFilter);

        ElasticSearchFilter elasticSearchFilter = new ESSearchFilter();
        elasticSearchFilter.index("sm-products");
        elasticSearchFilter.source("scope");
        elasticSearchFilter.query(boolFilter);
        elasticSearchFilter.sort(new SortFilter("3_factorValues.value", SortFilter.Sort.DESC));


        Response<List<Entity>> entities = client.elasticSearch()
                .filters(elasticSearchFilter)
                .limit(50)
                .fetch();

        Assert.assertNotNull(entities);
    }
}
