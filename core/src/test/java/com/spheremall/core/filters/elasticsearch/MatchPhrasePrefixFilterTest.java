package com.spheremall.core.filters.elasticsearch;

import com.spheremall.core.filters.elasticsearch.fulltext.MatchPhrasePrefixFilter;

import junit.framework.Assert;

import org.junit.Test;

public class MatchPhrasePrefixFilterTest {

    @Test
    public void testSimpleMatchQuery() {
        String expected = "{" +
                "\"match_phrase_prefix\":{" +
                "\"message\":\"quick brown f\"" +
                "}" +
                "}";

        MatchPhrasePrefixFilter matchFilter = new MatchPhrasePrefixFilter("message", "quick brown f");
        Assert.assertEquals(expected, matchFilter.toString());
    }

    @Test
    public void testMatchWithAnalyzer() {
        String expected = "{" +
                "\"match_phrase_prefix\":{" +
                "\"message\":{" +
                "\"query\":\"quick brown f\"," +
                "\"max_expansions\":10" +
                "}" +
                "}" +
                "}";

        MatchPhrasePrefixFilter matchFilter = new MatchPhrasePrefixFilter("message", "quick brown f");
        matchFilter.setMaxExpansions(10);
        Assert.assertEquals(expected, matchFilter.toString());
    }
}
