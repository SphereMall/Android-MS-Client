package com.spheremall.core.filters.elasticsearch;

import com.spheremall.core.exceptions.SphereMallException;
import com.spheremall.core.filters.elasticsearch.facets.ESAttributesFilterCriteria;
import com.spheremall.core.filters.elasticsearch.facets.ESAttributesRangeFilterCriteria;
import com.spheremall.core.filters.elasticsearch.facets.ESBrandsFilterCriteria;
import com.spheremall.core.filters.elasticsearch.facets.ESCatalogFilter;
import com.spheremall.core.filters.elasticsearch.facets.ESCatalogFilterCriteria;
import com.spheremall.core.filters.elasticsearch.facets.ESCatalogFilterImpl;
import com.spheremall.core.filters.elasticsearch.facets.ESFunctionalNamesFilterCriteria;
import com.spheremall.core.filters.elasticsearch.facets.ESPriceRangeFilterCriteria;
import com.spheremall.core.filters.elasticsearch.facets.configs.ESAttributesConfig;
import com.spheremall.core.filters.elasticsearch.facets.configs.ESBrandsConfig;
import com.spheremall.core.filters.elasticsearch.facets.configs.ESFactorValuesConfig;
import com.spheremall.core.filters.elasticsearch.facets.configs.ESFunctionalNamesConfig;
import com.spheremall.core.filters.elasticsearch.facets.configs.ESPriceRangeConfig;

import org.json.JSONException;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class ESCatalogFilterTest {

    @Test
    public void testBuildingFilter() throws JSONException, SphereMallException {

        String expectedConfig = "{" +
                "\"functionalNames\":\"true\"," +
                "\"brands\":\"true\"," +
                "\"attributes\":[\"reward\",\"minpricepoints\"]," +
                "\"priceRange\":\"true\"," +
                "\"factorValues\":[1,2,3]" +
                "}";

        ESCatalogFilterImpl catalogFilter = new ESCatalogFilterImpl(Arrays.asList(
                new ESPriceRangeConfig(),
                new ESFactorValuesConfig(Arrays.asList(1, 2, 3)),
                new ESBrandsConfig(),
                new ESAttributesConfig(Arrays.asList("reward", "minpricepoints")),
                new ESFunctionalNamesConfig()
        ));

        Assert.assertEquals(expectedConfig, catalogFilter.toConfig().toString());

        ESCatalogFilterCriteria criteria1 = new ESAttributesFilterCriteria("reward", "1", "0");
        ESCatalogFilterCriteria criteria2 = new ESAttributesFilterCriteria("color", "black", "white");
        ESCatalogFilterCriteria criteria3 = new ESPriceRangeFilterCriteria(100, 400);
        ESCatalogFilterCriteria criteria4 = new ESAttributesRangeFilterCriteria("minpricepoints", "1", "2", "3", "4", "5", "6");
        ESCatalogFilterCriteria criteria5 = new ESBrandsFilterCriteria(84);
        ESCatalogFilterCriteria criteria6 = new ESFunctionalNamesFilterCriteria(1, 3);

        catalogFilter.add(criteria1);
        catalogFilter.add(criteria2);
        catalogFilter.add(criteria3);
        catalogFilter.add(criteria4);
        catalogFilter.add(criteria5);
        catalogFilter.add(criteria6);
        Assert.assertNotNull(catalogFilter);
    }

    @Test
    public void testCreateBody() {
        ESCatalogFilterImpl catalogFilter = new ESCatalogFilterImpl(Arrays.asList(
                new ESPriceRangeConfig(),
                new ESFactorValuesConfig(Arrays.asList(1, 2, 3)),
                new ESBrandsConfig(),
                new ESAttributesConfig(Arrays.asList("reward", "minpricepoints")),
                new ESFunctionalNamesConfig()
        ));

        Assert.assertNull(catalogFilter.toBoolFilter());

        catalogFilter.add(new ESPriceRangeFilterCriteria(1, 2));
        Assert.assertNotNull(catalogFilter.toBoolFilter());
    }
}
