package com.spheremall.core.filters;

import com.spheremall.core.filters.grid.AttributeFilter;
import com.spheremall.core.filters.grid.BrandFilter;
import com.spheremall.core.filters.grid.EntityFilter;
import com.spheremall.core.filters.grid.FactorFilter;
import com.spheremall.core.filters.grid.FunctionalNameFilter;
import com.spheremall.core.filters.grid.GridFilter;
import com.spheremall.core.filters.grid.PriceRangeFilter;
import com.spheremall.core.resources.SetUpResourceTest;

import org.json.JSONException;
import org.junit.Assert;
import org.junit.Test;

public class GridFilterTest extends SetUpResourceTest {

    @Test
    public void testGridFilterSingleElement() {
        FunctionalNameFilter filter = new FunctionalNameFilter(6);
        Assert.assertEquals("functionalNames", filter.getName());
        Assert.assertEquals(6, filter.getValues().get(0).intValue());
    }

    @Test
    public void testGridFilterElements() {
        Integer[] values = new Integer[]{1, 2, 3};
        AttributeFilter attributeFilter = new AttributeFilter(values);
        Assert.assertEquals("attributes", attributeFilter.getName());

        for (int i = 0; i < values.length; i++) {
            Assert.assertEquals(values[i], attributeFilter.getValues().get(i));
        }
    }

    @Test
    public void testGridFilter() {
        Integer[] attributeValues = new Integer[]{1, 2, 3};
        AttributeFilter attributeFilter = new AttributeFilter(attributeValues);
        Assert.assertEquals("attributes", attributeFilter.getName());

        for (int i = 0; i < attributeValues.length; i++) {
            Assert.assertEquals(attributeValues[i], attributeFilter.getValues().get(i));
        }

        Integer[] functionalNamesValues = new Integer[]{1, 2, 3};
        FunctionalNameFilter functionalNamesFilter = new FunctionalNameFilter(functionalNamesValues);
        Assert.assertEquals("functionalNames", functionalNamesFilter.getName());

        for (int i = 0; i < functionalNamesValues.length; i++) {
            Assert.assertEquals(functionalNamesValues[i], functionalNamesFilter.getValues().get(i));
        }

        GridFilter gridFilter = new GridFilter();

        gridFilter
                .elements(functionalNamesFilter)
                .elements(attributeFilter);

        Assert.assertEquals("params=[{\"functionalNames\":[1,2,3]},{\"attributes\":[1,2,3]}]", gridFilter.toString());

        attributeFilter = new AttributeFilter(235);
        gridFilter = new GridFilter();
        gridFilter.elements(attributeFilter);
        Assert.assertEquals("params=[{\"attributes\":[235]}]", gridFilter.toString());

        EntityFilter entityFilter = new EntityFilter("products");
        BrandFilter brandFilter = new BrandFilter(1);
        gridFilter = new GridFilter();
        gridFilter
                .elements(entityFilter)
                .elements(brandFilter);

        Assert.assertEquals("params=[{\"entity\":[\"products\"]},{\"brands\":[1]}]", gridFilter.toString());
    }

    @Test
    public void testGridFilterParams() {

        Integer[] attr1Values = new Integer[]{1, 2, 3};
        Integer[] attr2Values = new Integer[]{3, 2, 4};

        AttributeFilter attr1 = new AttributeFilter(1, 2, 3);
        AttributeFilter attr2 = new AttributeFilter(3, 2, 4);
        Assert.assertEquals("attributes", attr1.getName());
        Assert.assertEquals("attributes", attr2.getName());
        for (int i = 0; i < attr1Values.length; i++) {
            Assert.assertEquals(attr1Values[i], attr1.getValues().get(i));
        }
        for (int i = 0; i < attr2Values.length; i++) {
            Assert.assertEquals(attr2Values[i], attr2.getValues().get(i));
        }

        Integer[] functionalNameFilterValues = new Integer[]{1, 2};

        FunctionalNameFilter functionalNameFilter = new FunctionalNameFilter(1, 2);
        Assert.assertEquals("functionalNames", functionalNameFilter.getName());
        for (int i = 0; i < functionalNameFilterValues.length; i++) {
            Assert.assertEquals(functionalNameFilterValues[i], functionalNameFilter.getValues().get(i));
        }

        AttributeFilter attr3 = new AttributeFilter(1, 5);

        GridFilter filter = new GridFilter();
        String stringParams = filter.elements(attr1, attr2)
                .elements(functionalNameFilter, attr3)
                .toString();

        Assert.assertEquals("params=[{\"attributes\":[1,2,3]},{\"attributes\":[3,2,4]},{\"functionalNames\":[1,2]},{\"attributes\":[1,5]}]", stringParams);
    }

    @Test
    public void testGridFilterWithPrice() {
        Integer[] attr1Values = new Integer[]{1, 2, 3};
        AttributeFilter attr1 = new AttributeFilter(1, 2, 3);

        Assert.assertEquals("attributes", attr1.getName());
        for (int i = 0; i < attr1Values.length; i++) {
            Assert.assertEquals(attr1Values[i], attr1.getValues().get(i));
        }


        Integer[] functionalNameFilterValues = new Integer[]{5};

        FunctionalNameFilter functionalNameFilter = new FunctionalNameFilter(5);
        Assert.assertEquals("functionalNames", functionalNameFilter.getName());
        for (int i = 0; i < functionalNameFilterValues.length; i++) {
            Assert.assertEquals(functionalNameFilterValues[i], functionalNameFilter.getValues().get(i));
        }

        Integer[] brandValues = new Integer[]{4};
        BrandFilter brandFilter = new BrandFilter(4);
        Assert.assertEquals("brands", brandFilter.getName());
        for (int i = 0; i < brandValues.length; i++) {
            Assert.assertEquals(brandValues[i], brandFilter.getValues().get(i));
        }

        Integer[] priceRangeValues = new Integer[]{1000, 5000};
        PriceRangeFilter priceRangeFilter = new PriceRangeFilter(1000, 5000);
        Assert.assertEquals("priceRange", priceRangeFilter.getName());
        for (int i = 0; i < priceRangeValues.length; i++) {
            Assert.assertEquals(priceRangeValues[i], priceRangeFilter.getValues().get(i));
        }

        GridFilter filter = new GridFilter();
        String stringParams = filter.elements(attr1, functionalNameFilter, brandFilter, priceRangeFilter).toString();

        Assert.assertEquals("params=[{\"attributes\":[1,2,3]},{\"functionalNames\":[5]},{\"brands\":[4]},{\"priceRange\":[1000,5000]}]", stringParams);
    }

    @Test
    public void testGridFilterWithFactors() {
        Integer[] attr1Values = new Integer[]{1, 2, 3};
        AttributeFilter attr1 = new AttributeFilter(1, 2, 3);

        Assert.assertEquals("attributes", attr1.getName());
        for (int i = 0; i < attr1Values.length; i++) {
            Assert.assertEquals(attr1Values[i], attr1.getValues().get(i));
        }

        Integer[] factorValues = new Integer[]{4};
        FactorFilter factorFilter = new FactorFilter(4);
        Assert.assertEquals("factors", factorFilter.getName());
        for (int i = 0; i < factorValues.length; i++) {
            Assert.assertEquals(factorValues[i], factorFilter.getValues().get(i));
        }

        GridFilter filter = new GridFilter();
        String stringParams = filter.elements(attr1, factorFilter).toString();

        Assert.assertEquals("params=[{\"attributes\":[1,2,3]},{\"factors\":[4]}]", stringParams);
    }

    @Test
    public void testGridReset() {
        Integer[] attr1Values = new Integer[]{1};
        AttributeFilter attr1 = new AttributeFilter(1);

        Assert.assertEquals("attributes", attr1.getName());
        for (int i = 0; i < attr1Values.length; i++) {
            Assert.assertEquals(attr1Values[i], attr1.getValues().get(i));
        }


        Integer[] functionalNameFilterValues = new Integer[]{5};

        FunctionalNameFilter functionalNameFilter = new FunctionalNameFilter(5);
        Assert.assertEquals("functionalNames", functionalNameFilter.getName());
        for (int i = 0; i < functionalNameFilterValues.length; i++) {
            Assert.assertEquals(functionalNameFilterValues[i], functionalNameFilter.getValues().get(i));
        }

        GridFilter filter = new GridFilter();
        filter.elements(attr1, functionalNameFilter);
        Assert.assertEquals("attributes", filter.getElements().get(0).getName());
        Assert.assertEquals("functionalNames", filter.getElements().get(1).getName());

        try {
            Assert.assertEquals(1, filter.getElements().get(0).asArray().get(0));
            Assert.assertEquals(5, filter.getElements().get(1).asArray().get(0));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        filter.reset();

        Assert.assertEquals(0, filter.getElements().size());
    }
}
