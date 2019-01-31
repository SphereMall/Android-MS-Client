package com.spheremall.core.filters.elasticsearch;

import com.spheremall.core.exceptions.SphereMallException;
import com.spheremall.core.filters.elasticsearch.facets.ESAttributesFilterCriteria;
import com.spheremall.core.filters.elasticsearch.facets.ESCatalogFilter;
import com.spheremall.core.filters.elasticsearch.facets.ESCatalogFilterCriteria;
import com.spheremall.core.filters.elasticsearch.facets.ESCatalogFilterImpl;
import com.spheremall.core.filters.elasticsearch.facets.ESPriceRangeFilterCriteria;

import org.json.JSONException;
import org.junit.Assert;
import org.junit.Test;

public class ESCatalogFilterTest {

    @Test
    public void testBuildingFilter() throws JSONException, SphereMallException {
        ESCatalogFilterImpl.Builder builder = new ESCatalogFilterImpl.Builder();
        ESCatalogFilter filter = ESCatalogFilterImpl.newInstance(builder);

        ESCatalogFilterCriteria criteria = new ESAttributesFilterCriteria("reward", "1", "0");
        filter.add("reward", criteria);

        ESCatalogFilterCriteria criteria2 = new ESPriceRangeFilterCriteria(100, 400);
//        filter.add()

        Assert.assertNotNull(filter.asJson());
    }
}
