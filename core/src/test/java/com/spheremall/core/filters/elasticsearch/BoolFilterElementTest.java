package com.spheremall.core.filters.elasticsearch;

import com.spheremall.core.filters.elasticsearch.compound.BoolFilter;
import com.spheremall.core.filters.elasticsearch.criterions.TermsFilterCriteria;
import com.spheremall.core.filters.elasticsearch.fulltext.MatchFilter;
import com.spheremall.core.filters.elasticsearch.terms.TermsFilter;

import junit.framework.Assert;

import org.junit.Test;

public class BoolFilterElementTest {

    @Test
    public void testBoolFilter() {
        String expectedFilter = ("{\"bool\" : {" +
                "\"should\" : [" +
                "{ \"term\" : {\"price\" : \"20\"}}," +
                "{ \"term\" : {\"productID\" : \"XHDK-A-1293-#fJ3\"}}]," +
                "\"must\" : " +
                "{\"term\" : {\"price\" : \"30\"}}}}")
                .replace(" ", "");

        TermsFilter shouldPriceTerm = new TermsFilter(new TermsFilterCriteria("price", "20"));
        TermsFilter shouldProductIdTerm = new TermsFilter(new TermsFilterCriteria("productID", "XHDK-A-1293-#fJ3"));

        TermsFilter mustPriceTerm = new TermsFilter(new TermsFilterCriteria("price", "30"));

        BoolFilter element = new BoolFilter();
        element.should(shouldPriceTerm, shouldProductIdTerm);
        element.must(mustPriceTerm);
        Assert.assertEquals(expectedFilter, element.toString());
    }

    @Test
    public void testMustNotBoolFilter() {
        String expectedFilter = ("{\"bool\" : {" +
                "\"must_not\" : " +
                "{\"term\" : {\"price\" : \"30\"}}}}")
                .replace(" ", "");

        TermsFilter mustPriceTerm = new TermsFilter(new TermsFilterCriteria("price", "30"));

        BoolFilter element = new BoolFilter();
        element.mustNot(mustPriceTerm);
        Assert.assertEquals(expectedFilter, element.toString());
    }

    @Test
    public void testNestedBoolFilter() {
        String expectedFilter = ("{" +
                "\"bool\":{" +
                "\"should\":[{" +
                "\"term\":{" +
                "\"productID\":\"KDKE-B-9947-#kL5\"" +
                "}" +
                "}," +
                "{" +
                "\"bool\":{" +
                "\"must\":[{" +
                "\"term\":{" +
                "\"productID\":\"JODL-X-1937-#pV7\"" +
                "}" +
                "}," +
                "{" +
                "\"term\":{" +
                "\"price\":\"30\"}}]}}]}}").replace(" ", "");
        TermsFilter shouldProductIdTerm = new TermsFilter(new TermsFilterCriteria("productID", "KDKE-B-9947-#kL5"));
        TermsFilter nestedMustProductIdTerm = new TermsFilter(new TermsFilterCriteria("productID", "JODL-X-1937-#pV7"));
        TermsFilter nestedMustPriceTerm = new TermsFilter(new TermsFilterCriteria("price", "30"));

        BoolFilter nestedBoolFilter = new BoolFilter();
        nestedBoolFilter.must(nestedMustProductIdTerm, nestedMustPriceTerm);


        BoolFilter rootBoolFilter = new BoolFilter();
        rootBoolFilter.should(shouldProductIdTerm, nestedBoolFilter);

        Assert.assertEquals(expectedFilter, rootBoolFilter.toString());
    }

    @Test
    public void testBoolWithMatch(){
        String expectedFilter = ("{\"bool\" : {" +
                "\"must_not\" : " +
                "{\"match\" : {\"title\" : \"30\"}}}}")
                .replace(" ", "");

        MatchFilter matchFilter =new MatchFilter("title", "30");

        BoolFilter element = new BoolFilter();
        element.mustNot(matchFilter);
        Assert.assertEquals(expectedFilter, element.toString());
    }
}
