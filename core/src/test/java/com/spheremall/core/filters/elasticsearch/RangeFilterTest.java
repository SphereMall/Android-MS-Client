package com.spheremall.core.filters.elasticsearch;

import com.spheremall.core.filters.elasticsearch.terms.PriceRangeFilter;
import com.spheremall.core.filters.elasticsearch.terms.RangeFilter;
import com.spheremall.core.filters.elasticsearch.terms.TextAttributesRangeFilter;

import org.junit.Assert;
import org.junit.Test;

public class RangeFilterTest {

    @Test
    public void testPriceRangeFilter() {
        String expectedJson = ("{" +
                "\"range\": {" +
                "\"price\": {" +
                "\"lt\": 40," +
                "\"gt\": 20}}}").replace(" ", "");

        PriceRangeFilter filterElement = new PriceRangeFilter("price");
        filterElement.addOption("gt", 20.0);
        filterElement.addOption("lt", 40.0);

        Assert.assertEquals(expectedJson, filterElement.toString());


        filterElement = new PriceRangeFilter("price");
        filterElement.setRange(20., 40.);

        Assert.assertEquals(expectedJson, filterElement.toString());

    }

    @Test
    public void testTextRangeFilter() {
        String expectedString = ("" +
                "{" +
                "    \"range\": {" +
                "        \"timestamp\": {" +
                "            \"lt\": \"2014-01-07T00:00:00\"," +
                "            \"gt\": \"2014-01-01T00:00:00\"" +
                "        }" +
                "    }" +
                "}").replace(" ", "");

        TextAttributesRangeFilter rangeElement = new TextAttributesRangeFilter("timestamp");
        rangeElement.addOption(RangeFilter.RangeType.GT, "2014-01-01T00:00:00");
        rangeElement.addOption(RangeFilter.RangeType.LT, "2014-01-07T00:00:00");
        Assert.assertEquals(expectedString, rangeElement.toString());
    }
}
