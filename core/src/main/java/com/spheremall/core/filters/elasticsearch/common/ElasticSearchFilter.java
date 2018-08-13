package com.spheremall.core.filters.elasticsearch.common;

public interface ElasticSearchFilter {

    ElasticSearchFilter index(String... indexes);

    ElasticSearchFilter query(ElasticSearchQuery elasticSearchQuery);
}
