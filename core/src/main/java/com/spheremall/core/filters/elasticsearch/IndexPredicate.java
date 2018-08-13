package com.spheremall.core.filters.elasticsearch;

import com.spheremall.core.filters.elasticsearch.common.ElasticSearchQuery;

import java.util.ArrayList;
import java.util.List;

public class IndexPredicate {
    public final IndexFilter indexFilter;
    public final ArrayList<ElasticSearchQuery> searchQueries = new ArrayList<>();

    public IndexPredicate(IndexFilter indexFilter) {
        this.indexFilter = indexFilter;
    }

    public void add(ElasticSearchQuery searchQuery) {
        this.searchQueries.add(searchQuery);
    }

    public void addAll(List<ElasticSearchQuery> searchQueries) {
        this.searchQueries.addAll(searchQueries);
    }
}
