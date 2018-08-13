package com.spheremall.core.filters.elasticsearch;

import com.spheremall.core.filters.elasticsearch.fulltext.MatchFilter;

import junit.framework.Assert;

import org.junit.Test;

public class MatchFilterTest {

    @Test
    public void testSimpleMatchQuery() {
        String expected = ("{" +
                "\"match\":{" +
                "\"title\":\"this is a test\"" +
                "}}");

        MatchFilter matchFilter = new MatchFilter("title", "this is a test");
        Assert.assertEquals(expected, matchFilter.toString());
    }

    @Test
    public void testMatchWithOperator() {
        String expected = ("{" +
                "\"match\":{" +
                "\"title\":{" +
                "\"query\":\"this is a test\"," +
                "\"zero_terms_query\":\"all\"," +
                "\"operator\":\"and\"" +
                "}" +
                "}" +
                "}");

        MatchFilter matchFilter = new MatchFilter("title", "this is a test");
        matchFilter.setZeroTermsQuery("all");
        matchFilter.setOperator("and");
        Assert.assertEquals(expected, matchFilter.toString());
    }

    @Test
    public void testMatchWithFrequency() {
        String expected = "{" +
                "\"match\":{" +
                "\"title\":{" +
                "\"cutoff_frequency\":0.001," +
                "\"query\":\"to be or not to be\"" +
                "}" +
                "}" +
                "}";

        MatchFilter matchFilter = new MatchFilter("title", "to be or not to be");
        matchFilter.setCutoffFrequency(0.001);
        Assert.assertEquals(expected, matchFilter.toString());
    }

    @Test
    public void testMatchWithAutoGenerateSynonyms() {
        String expected = "{" +
                "\"match\":{" +
                "\"title\":{" +
                "\"auto_generate_synonyms_phrase_query\":false," +
                "\"query\":\"to be or not to be\"" +
                "}" +
                "}" +
                "}";

        MatchFilter matchFilter = new MatchFilter("title", "to be or not to be");
        matchFilter.setAutoGenerateSynonymsPhraseQuery(false);
        Assert.assertEquals(expected, matchFilter.toString());
    }
}
