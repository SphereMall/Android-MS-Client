package com.spheremall.core.resources.elasticSearch;

import com.spheremall.core.entities.Entity;
import com.spheremall.core.entities.products.Product;
import com.spheremall.core.entities.products.ProductAttributeValue;
import com.spheremall.core.exceptions.SphereMallException;
import com.spheremall.core.filters.elasticsearch.ESSearchFilter;
import com.spheremall.core.filters.elasticsearch.compound.BoolFilter;
import com.spheremall.core.filters.elasticsearch.criterions.TermsFilterCriteria;
import com.spheremall.core.filters.elasticsearch.terms.TermsFilter;
import com.spheremall.core.resources.SetUpResourceTest;

import junit.framework.Assert;

import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class ElasticSearchResourceTest extends SetUpResourceTest {

    @Test
    public void testSetFilter() throws IOException, SphereMallException {
        ElasticSearchResource resource = client.elasticSearch();

        TermsFilter urlCodeTermsFilter = new TermsFilter(new TermsFilterCriteria("urlCode", "m7s-ltd-incl-400wh-8713568337908"));

        ESSearchFilter filter = new ESSearchFilter();
        filter.index("sm-products")
                .query(urlCodeTermsFilter);

        resource.filters(filter);

        String response = resource.allTest();
        Assert.assertNotNull(response);
    }

    @Test
    public void testSetCompoundFilter() throws IOException, SphereMallException {

        TermsFilter termsFilter = new TermsFilter(new TermsFilterCriteria("variantsCompound", "fd4e12b4-df24-8882-d068-b73d26949e1f"));

        BoolFilter boolFilter = new BoolFilter();
        boolFilter.filter(termsFilter);

        ESSearchFilter filter = new ESSearchFilter();
        filter.index("sm-products", "sm-documents")
                .query(boolFilter);

        List<Entity> entities = client.elasticSearch()
                .filters(filter)
                .fetch();

        Assert.assertNotNull(entities);

        for (Entity entity : entities) {
            Product product = (Product) entity;
            for (ProductAttributeValue attr : product.productAttributeValues) {
                if (attr.attributes.get(0).code.equals("colorbas")) {
                    System.out.println("found");
                }
            }
        }
    }
}
