package com.spheremall.core.filters.elasticsearch;

import com.spheremall.core.filters.elasticsearch.criterions.AttributeFilterCriteria;
import com.spheremall.core.filters.elasticsearch.criterions.TermsFilterCriteria;
import com.spheremall.core.filters.elasticsearch.terms.TermsFilter;

import org.junit.Assert;
import org.junit.Test;

public class TermsFilterElementTest {

    @Test
    public void testTermsFilter() {
        String expectedJson = "{\"terms\":{\"price\":[\"20\",\"30\"]}}";

        TermsFilter termsFilter = new TermsFilter(new TermsFilterCriteria("price", "20", "30"));
        Assert.assertEquals(expectedJson, termsFilter.toString());
    }

    @Test
    public void testTermsFilterWithAttributesCriteria() {
        String expectedJson = "{\"terms\":{\"7_attr.valueId\":[279,554]}}";
        TermsFilter termsFilter = new TermsFilter(new AttributeFilterCriteria(7, 279, 554));
        Assert.assertEquals(expectedJson, termsFilter.toString());
    }
}
