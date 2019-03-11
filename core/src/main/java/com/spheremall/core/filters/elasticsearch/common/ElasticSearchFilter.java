package com.spheremall.core.filters.elasticsearch.common;

import com.spheremall.core.filters.elasticsearch.criterions.SortFilter;
import com.spheremall.core.specifications.base.FilterSpecification;

public interface ElasticSearchFilter extends FilterSpecification {

    String indexes();

    ElasticSearchFilter index(String... indexes);

    ElasticSearchFilter query(ElasticSearchQuery elasticSearchQuery);

    ElasticSearchFilter sort(SortFilter sortFilter);

    ElasticSearchFilter source(String... fields);

    ElasticSearchFilter setSize(int limit);

    ElasticSearchFilter setFrom(int offset);
}
