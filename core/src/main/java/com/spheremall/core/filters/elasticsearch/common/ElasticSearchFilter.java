package com.spheremall.core.filters.elasticsearch.common;

import com.spheremall.core.filters.elasticsearch.criterions.SortFilter;

public interface ElasticSearchFilter {

    ElasticSearchFilter index(String... indexes);

    ElasticSearchFilter query(ElasticSearchQuery elasticSearchQuery);

    ElasticSearchFilter sort(SortFilter sortFilter);

    ElasticSearchFilter source(String... fields);
}
