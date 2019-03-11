package com.spheremall.core.filters.elasticsearch;

import com.spheremall.core.entities.Entity;
import com.spheremall.core.entities.Response;
import com.spheremall.core.exceptions.SphereMallException;
import com.spheremall.core.filters.elasticsearch.compound.BoolFilter;
import com.spheremall.core.filters.elasticsearch.criterions.SortFilter;
import com.spheremall.core.filters.elasticsearch.criterions.TermsFilterCriteria;
import com.spheremall.core.filters.elasticsearch.terms.TermsFilter;
import com.spheremall.core.resources.SetUpResourceTest;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class MultiSearchFilterTest extends SetUpResourceTest {

    @Test
    public void testCreateMultiSearchFilter() {
        ESSearchFilter filter1 = createFirstFilter();
        ESSearchFilter filter2 = createSecondFilter();

        ESMultiSearchFilter filter = new ESMultiSearchFilter();
        String body = filter
                .addFilter(filter1)
                .addFilter(filter2)
                .asBody();

        Assert.assertNotNull(body);
    }

    @Test
    public void testGetEntitiesWithMultiSearch() throws IOException, SphereMallException {
        ESSearchFilter filter1 = createFirstFilter();
        ESSearchFilter filter2 = createSecondFilter();

        ESMultiSearchFilter filter = new ESMultiSearchFilter();
        filter
                .addFilter(filter1)
                .addFilter(filter2);

        List<Response<List<Entity>>> entities = client.elasticSearch()
                .filters(filter.asFilter())
                .multiSearch();

        Assert.assertNotNull(entities);
    }

    private ESSearchFilter createFirstFilter() {
        ESSearchFilter filter1 = new ESSearchFilter();
        filter1.index("sm-products");
        filter1.sort(new SortFilter("price", SortFilter.Sort.DESC));
        filter1.source("scope");
        filter1.setSize(5);
        TermsFilter termsFilter1 = new TermsFilter(new TermsFilterCriteria("isMain", "1"));
        BoolFilter boolFilter1 = new BoolFilter();
        boolFilter1.must(termsFilter1);
        filter1.query(boolFilter1);
        return filter1;
    }

    private ESSearchFilter createSecondFilter() {
        ESSearchFilter filter1 = new ESSearchFilter();
        filter1.index("sm-documents");
        filter1.source("scope");
        filter1.setSize(2);
        TermsFilter termsFilter1 = new TermsFilter(new TermsFilterCriteria("visible", "1"));
        BoolFilter boolFilter1 = new BoolFilter();
        boolFilter1.must(termsFilter1);
        filter1.query(boolFilter1);
        return filter1;
    }
}
