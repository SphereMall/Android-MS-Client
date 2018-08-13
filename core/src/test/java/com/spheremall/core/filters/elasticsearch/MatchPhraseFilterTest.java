package com.spheremall.core.filters.elasticsearch;

import com.spheremall.core.filters.elasticsearch.fulltext.MatchPhraseFilter;

import junit.framework.Assert;

import org.junit.Test;

public class MatchPhraseFilterTest {

    @Test
    public void testSimpleMatchQuery() {
        String expected = ("{" +
                "\"match_phrase\":{" +
                "\"message\":\"this is a test\"" +
                "}}");

        MatchPhraseFilter matchFilter = new MatchPhraseFilter("message", "this is a test");
        Assert.assertEquals(expected, matchFilter.toString());
    }

    @Test
    public void testMatchWithAnalyzer() {
        String expected = "{" +
                "\"match_phrase\":{" +
                "\"message\":{" +
                "\"analyzer\":\"my_analyzer\"," +
                "\"query\":\"this is a test\"" +
                "}" +
                "}" +
                "}";

        MatchPhraseFilter matchFilter = new MatchPhraseFilter("message", "this is a test");
        matchFilter.setAnalyzer("my_analyzer");
        Assert.assertEquals(expected, matchFilter.toString());
    }
}
