package com.spheremall.core.filters.elasticsearch;

import com.spheremall.core.filters.elasticsearch.criterions.SortFilter;

import org.junit.Assert;
import org.junit.Test;

public class ESEntitiesSortTest {

    @Test
    public void testSortAsc() {
        String expectedSortString = "{\"sort\":[{" +
                "\"price\":{" +
                "\"order\":\"desc\"}}]}";

        SortFilter sortFilter = new SortFilter("price", SortFilter.Sort.DESC);
        Assert.assertEquals(expectedSortString, sortFilter.toJson().toString());
    }
}
