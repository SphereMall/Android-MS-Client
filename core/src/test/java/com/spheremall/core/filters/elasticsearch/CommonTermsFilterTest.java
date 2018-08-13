package com.spheremall.core.filters.elasticsearch;

import com.spheremall.core.filters.elasticsearch.fulltext.CommonTermsFilter;

import junit.framework.Assert;

import org.junit.Test;

public class CommonTermsFilterTest {

    @Test
    public void testFilterWithFreqAndOperator() {

        String expected = "{" +
                "\"common\":{" +
                "\"body\":{" +
                "\"cutoff_frequency\":0.001," +
                "\"query\":\"this is bonsai cool\"," +
                "\"low_freq_operator\":\"and\"" +
                "}" +
                "}" +
                "}";

        CommonTermsFilter commonTermsFilter = new CommonTermsFilter("this is bonsai cool");
        commonTermsFilter.setCutoffFrequency(0.001);
        commonTermsFilter.setLowFreqOperator("and");
        Assert.assertEquals(expected, commonTermsFilter.toString());
    }

    @Test
    public void testFilterWithMinShouldMatch() {
        String expected = "{" +
                "\"common\":{" +
                "\"body\":{" +
                "\"cutoff_frequency\":0.001," +
                "\"query\":\"nelly the elephant as a cartoon\"," +
                "\"minimum_should_match\":2" +
                "}" +
                "}" +
                "}";

        CommonTermsFilter commonTermsFilter = new CommonTermsFilter("nelly the elephant as a cartoon");
        commonTermsFilter.setCutoffFrequency(0.001);
        commonTermsFilter.setMinShouldMatch(2);
        Assert.assertEquals(expected, commonTermsFilter.toString());
    }

    @Test
    public void testFilterWithMinShouldMatchPercentage() {
        String expected = "{" +
                "\"common\":{" +
                "\"body\":{" +
                "\"cutoff_frequency\":0.001," +
                "\"query\":\"nelly the elephant as a cartoon\"," +
                "\"minimum_should_match\":\"%75\"" +
                "}" +
                "}" +
                "}";

        CommonTermsFilter commonTermsFilter = new CommonTermsFilter("nelly the elephant as a cartoon");
        commonTermsFilter.setCutoffFrequency(0.001);
        commonTermsFilter.setMinShouldMatch("%75");
        Assert.assertEquals(expected, commonTermsFilter.toString());
    }

    @Test
    public void testFilterWithMinShouldMatchLowAndHighFreq() {
        String expected = "{" +
                "\"common\":{" +
                "\"body\":{" +
                "\"cutoff_frequency\":0.001," +
                "\"query\":\"how not to be\"," +
                "\"minimum_should_match\":{" +
                "\"high_freq\":3," +
                "\"low_freq\":2" +
                "}" +
                "}" +
                "}" +
                "}";

        CommonTermsFilter commonTermsFilter = new CommonTermsFilter("how not to be");
        commonTermsFilter.setCutoffFrequency(0.001);
        commonTermsFilter.setMinShouldMatch(2, 3);
        Assert.assertEquals(expected, commonTermsFilter.toString());
    }
}
