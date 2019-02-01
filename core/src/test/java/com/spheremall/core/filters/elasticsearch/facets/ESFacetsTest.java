package com.spheremall.core.filters.elasticsearch.facets;

import com.spheremall.core.filters.elasticsearch.facets.models.ESAttributeModel;
import com.spheremall.core.filters.elasticsearch.facets.models.ESBrandModel;
import com.spheremall.core.filters.elasticsearch.facets.models.ESFacets;
import com.spheremall.core.filters.elasticsearch.facets.models.ESFactorValueModel;
import com.spheremall.core.filters.elasticsearch.facets.models.ESFunctionalNameModel;
import com.spheremall.core.filters.elasticsearch.facets.models.ESPriceRangeModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

public class ESFacetsTest {

    private ESFacets facets = new ESFacets();

    @Before
    public void setUp() {
        prepareFacetsAttributes();
        prepareFacetsPriceRange();
        prepareBrands();
        prepareFactorValues();
        prepareFunctionalNames();
    }

    @Test
    public void testBuildingAttributes() throws JSONException {
        String expectedResult = "{\"reward\":{\"value\":[\"1\",\"0\"]},\"test\":{\"value\":[\"test1\",\"test2\"]}}";
        JSONObject object = facets.attributesObject();
        Assert.assertEquals(expectedResult, object.toString());
    }

    @Test
    public void testBuildingPriceRange() throws JSONException {
        String expectedResult = "[{\"min\":0,\"max\":100}]";
        JSONArray object = facets.priceRangeObject();
        Assert.assertEquals(expectedResult, object.toString());
    }

    @Test
    public void testBuildingBrands() {
        String expectedResult = "[98,99]";
        JSONArray object = facets.brandsObject();
        Assert.assertEquals(expectedResult, object.toString());
    }

    @Test
    public void testBuildingFunctionalNames() {
        String expectedResult = "[98,99]";
        JSONArray object = facets.functionalNamesObject();
        Assert.assertEquals(expectedResult, object.toString());
    }

    @Test
    public void testBuildingFactorValues() {
        String expectedResult = "[98,99]";
        JSONArray object = facets.factorValuesObject();
        Assert.assertEquals(expectedResult, object.toString());
    }

    @Test
    public void testBuildingFacetsParams() {
        String expectedParams = "[{" +
                "\"functionalNames\":[98,99]," +
                "\"brands\":[98,99]," +
                "\"attributes\":{\"reward\":{\"value\":[\"1\",\"0\"]},\"test\":{\"value\":[\"test1\",\"test2\"]}}," +
                "\"priceRange\":[{\"min\":0,\"max\":100}]," +
                "\"factorValues\":[98,99]}]";

        Assert.assertEquals(expectedParams, facets.buildParams().toString());
    }

    @Test
    public void testUsingFilter() {
//        val attributesCriteria = ESAttributesFilterCriteria();
//        attributesCriteria.add("color", ["black", "white"]);
//        attributesCriteria.add("size", ["100", "200"]);
    }

    @After
    public void tearDown() {
        facets = null;
    }

    private void prepareFacetsAttributes() {
        ESAttributeModel attr1 = new ESAttributeModel();
        attr1.code = "reward";
        attr1.value = "1";

        ESAttributeModel attr2 = new ESAttributeModel();
        attr2.code = "reward";
        attr2.value = "0";

        ESAttributeModel attr3 = new ESAttributeModel();
        attr3.code = "test";
        attr3.value = "test1";

        ESAttributeModel attr4 = new ESAttributeModel();
        attr4.code = "test";
        attr4.value = "test2";

        facets.attributes.addAll(Arrays.asList(attr1, attr2, attr3, attr4));
    }

    private void prepareFacetsPriceRange() {
        ESPriceRangeModel model = new ESPriceRangeModel();
        model.minPrice = 0;
        model.maxPrice = 100;
        facets.priceRangeModel = model;
    }

    private void prepareBrands() {
        ESBrandModel model1 = new ESBrandModel();
        model1.id = 98;

        ESBrandModel model2 = new ESBrandModel();
        model2.id = 99;
        facets.brands.addAll(Arrays.asList(model1, model2));
    }

    private void prepareFactorValues() {
        ESFactorValueModel model1 = new ESFactorValueModel();
        model1.id = 98;

        ESFactorValueModel model2 = new ESFactorValueModel();
        model2.id = 99;
        facets.factorValues.addAll(Arrays.asList(model1, model2));
    }

    private void prepareFunctionalNames() {
        ESFunctionalNameModel model1 = new ESFunctionalNameModel();
        model1.id = 98;

        ESFunctionalNameModel model2 = new ESFunctionalNameModel();
        model2.id = 99;
        facets.functionalNames.addAll(Arrays.asList(model1, model2));
    }
}
