package com.spheremall.core.resources.elasticSearch;

import com.spheremall.core.exceptions.SphereMallException;
import com.spheremall.core.filters.elasticsearch.ESSearchFilter;
import com.spheremall.core.filters.elasticsearch.criterions.TermsFilterCriteria;
import com.spheremall.core.filters.elasticsearch.terms.TermsFilter;
import com.spheremall.core.resources.SetUpResourceTest;

import junit.framework.Assert;

import org.junit.Test;

import java.io.IOException;

public class ElasticSearchResourceTest extends SetUpResourceTest {

    @Test
    public void testSetFilter() throws IOException, SphereMallException {
        ElasticSearchResource resource = client.elasticSearch();

        TermsFilter urlCodeTermsFilter = new TermsFilter(new TermsFilterCriteria("urlCode", "plus-stores-plisses-tamisant-saumon-60431"));

        ESSearchFilter filter = new ESSearchFilter();
        filter.index("sm-products")
                .query(urlCodeTermsFilter);

        resource.filters(filter);

        String response = resource.allTest();
        Assert.assertNotNull(response);
    }
}
