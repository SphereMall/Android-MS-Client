package com.spheremall.core.filters.elasticsearch;

import com.spheremall.core.filters.elasticsearch.compound.BoolFilter;
import com.spheremall.core.filters.elasticsearch.criterions.SortFilter;
import com.spheremall.core.filters.elasticsearch.criterions.TermsFilterCriteria;
import com.spheremall.core.filters.elasticsearch.fulltext.MatchFilter;
import com.spheremall.core.filters.elasticsearch.terms.TermsFilter;

import org.junit.Assert;
import org.junit.Test;

public class ESSearchFilterTest {

    @Test
    public void testTermQuery() {
        TermsFilter termsFilter = new TermsFilter(new TermsFilterCriteria("brandId", "333"));

        ESSearchFilter searchFilter = new ESSearchFilter();
        searchFilter.index("sm-products")
                .query(termsFilter);

        String searchFilterString = searchFilter.toString();

        Assert.assertNotNull(searchFilterString);
    }

    @Test
    public void testMatchQuery() {


        MatchFilter matchFilter = new MatchFilter("title_fr", "Product");

        ESSearchFilter searchFilter = new ESSearchFilter();
        searchFilter.index("sm-products")
                .query(matchFilter);

        String searchFilterString = searchFilter.toString();

        Assert.assertNull(searchFilterString);
    }

    @Test
    public void testBoolQuery() {

        TermsFilter termsFilter = new TermsFilter(new TermsFilterCriteria("brandId", "333"));
        TermsFilter termsFilter2 = new TermsFilter(new TermsFilterCriteria("isMain", "1"));
        BoolFilter boolFilter = new BoolFilter();
        boolFilter.must(termsFilter, termsFilter2);

        ESSearchFilter searchFilter = new ESSearchFilter();
        searchFilter.index("sm-products")
                .query(boolFilter);

        String searchFilterString = searchFilter.toString();

        Assert.assertNotNull(searchFilterString);
    }

    @Test
    public void testBoolQueryAndSort() {

        TermsFilter termsFilter = new TermsFilter(new TermsFilterCriteria("brandId", "333"));
        TermsFilter termsFilter2 = new TermsFilter(new TermsFilterCriteria("isMain", "1"));
        BoolFilter boolFilter = new BoolFilter();
        boolFilter.must(termsFilter, termsFilter2);

        ESSearchFilter searchFilter = new ESSearchFilter();
        searchFilter.index("sm-products")
                .sort(new SortFilter("price", SortFilter.Sort.ASC))
                .source("price")
                .query(boolFilter);

        String searchFilterString = searchFilter.toString();

        Assert.assertNotNull(searchFilterString);
    }
}
