package com.spheremall.core.filters.elasticsearch;

import com.spheremall.core.exceptions.SphereMallException;
import com.spheremall.core.filters.elasticsearch.facets.ESAttributesFilterCriteria;
import com.spheremall.core.filters.elasticsearch.facets.ESBrandsFilterCriteria;
import com.spheremall.core.filters.elasticsearch.facets.ESCatalogFilterCriteria;
import com.spheremall.core.filters.elasticsearch.facets.ESCatalogFilterImpl;
import com.spheremall.core.filters.elasticsearch.facets.ESFunctionalNamesFilterCriteria;
import com.spheremall.core.filters.elasticsearch.facets.ESRangeFilterCriteria;
import com.spheremall.core.filters.elasticsearch.facets.configs.ESAttributesConfig;
import com.spheremall.core.filters.elasticsearch.facets.configs.ESBrandsConfig;
import com.spheremall.core.filters.elasticsearch.facets.configs.ESFactorValuesConfig;
import com.spheremall.core.filters.elasticsearch.facets.configs.ESFunctionalNamesConfig;
import com.spheremall.core.filters.elasticsearch.facets.configs.ESPriceRangeConfig;

import org.json.JSONException;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class ESCatalogFilterTest {

    @Test
    public void testBuildingFilter() throws JSONException, SphereMallException {

        String expectedConfig = "{\"functionalNames\":\"true\",\"brands\":\"true\",\"range\":{\"attributes\":[\"minpricepoints\"],\"fields\":[\"price\"]},\"attributes\":[\"reward\"],\"factorValues\":[1,2,3]}";

        ESCatalogFilterImpl catalogFilter = new ESCatalogFilterImpl(Arrays.asList(
                ESPriceRangeConfig.builder()
                        .addAttrCodes("minpricepoints")
                        .addFields("price")
                        .create(),
                new ESFactorValuesConfig(Arrays.asList(1, 2, 3)),
                new ESBrandsConfig(),
                new ESAttributesConfig(Collections.singletonList("reward")),
                new ESFunctionalNamesConfig()
        ));

        Assert.assertEquals(expectedConfig, catalogFilter.toConfig().toString());

        ESCatalogFilterCriteria criteria1 = new ESAttributesFilterCriteria("reward", "1", "0");
        ESCatalogFilterCriteria criteria2 = new ESAttributesFilterCriteria("color", "black", "white");
        ESCatalogFilterCriteria criteria5 = new ESBrandsFilterCriteria(84);
        ESCatalogFilterCriteria criteria6 = new ESFunctionalNamesFilterCriteria(1, 3);

        catalogFilter.add(criteria1);
        catalogFilter.add(criteria2);
        catalogFilter.add(criteria5);
        catalogFilter.add(criteria6);
        Assert.assertNotNull(catalogFilter);

        Assert.assertEquals(6, catalogFilter.count());
    }

    @Test
    public void testCreateBody() {
        ESCatalogFilterImpl catalogFilter = new ESCatalogFilterImpl(Arrays.asList(
                ESPriceRangeConfig.builder()
                        .addAttrCodes("minpricepoints")
                        .addFields("price")
                        .create(),
                new ESFactorValuesConfig(Arrays.asList(1, 2, 3)),
                new ESBrandsConfig(),
                new ESAttributesConfig(Collections.singletonList("reward")),
                new ESFunctionalNamesConfig()
        ));

        Assert.assertNull(catalogFilter.toBoolFilter());
    }

    @Test
    public void testRangeFilter() throws JSONException, SphereMallException {
        String expectedParams = "[{\"range\":{\"attributes\":{\"minpricepoints\":{\"gte\":0,\"lte\":50000},\"minpriceeuro\":{\"gte\":0,\"lte\":50000}},\"fields\":{\"price\":{\"gte\":0,\"lte\":79000}}},\"attributes\":{\"color\":{\"value\":[\"black\",\"white\"]}}}]";

        ESCatalogFilterImpl catalogFilter = new ESCatalogFilterImpl(new ArrayList<>());
        ESRangeFilterCriteria criteria = ESRangeFilterCriteria.builder()
                .addFieldRange("price", 0, 79000)
                .addAttributeRange("minpricepoints", 0, 50000)
                .addAttributeRange("minpriceeuro", 0, 50000)
                .create();

        ESCatalogFilterCriteria criteria2 = new ESAttributesFilterCriteria("color", "black", "white");

        catalogFilter.add(criteria);
        catalogFilter.add(criteria2);

        Assert.assertEquals(expectedParams, catalogFilter.toParams().toString());

        String expectedQuery = "{\"bool\":{\"must\":[{\"range\":{\"minpricepoints_attr.attributeValue\":{\"lt\":50000,\"gt\":0}}},{\"range\":{\"minpriceeuro_attr.attributeValue\":{\"lt\":50000,\"gt\":0}}},{\"range\":{\"price\":{\"lt\":79000,\"gt\":0}}},{\"terms\":{\"color_attr.attributeValue\":[\"black\",\"white\"]}}]}}";

        Assert.assertEquals(expectedQuery, catalogFilter.toBoolFilter().toString());
    }
}
