package com.spheremall.core.filters.elasticsearch;

import com.spheremall.core.filters.elasticsearch.fulltext.MultiMatchFilter;

import org.junit.Assert;
import org.junit.Test;

public class MultiMatchFilterTest {

    @Test
    public void testMultiMatchFilter() {
        String expected = "{" +
                "\"multi_match\":{" +
                "\"analyzer\":\"standard\"," +
                "\"query\":\"brown fox\"," +
                "\"tie_breaker\":0.3," +
                "\"type\":\"best_fields\"," +
                "\"fields\":[\"subject\",\"message\"]," +
                "\"operator\":\"and\"" +
                "}" +
                "}";

        MultiMatchFilter multiMatchFilter = new MultiMatchFilter("message", "brown fox");
        multiMatchFilter.setType("best_fields");
        multiMatchFilter.setFields("subject", "message");
        multiMatchFilter.setTieBreaker(0.3);
        multiMatchFilter.setAnalyzer("standard");
        multiMatchFilter.setOperator("and");

        Assert.assertEquals(expected, multiMatchFilter.toString());
    }
}