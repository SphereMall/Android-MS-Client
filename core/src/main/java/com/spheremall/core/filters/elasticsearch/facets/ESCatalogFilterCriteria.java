package com.spheremall.core.filters.elasticsearch.facets;

import com.spheremall.core.filters.elasticsearch.common.ElasticSearchQuery;
import com.spheremall.core.filters.elasticsearch.facets.configs.ESCatalogConfig;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public interface ESCatalogFilterCriteria {
    String name();

    ESCatalogFilterCriteria update(ESCatalogFilterCriteria newCriteria);

    JSONObject toJson(List<ESCatalogConfig> configs) throws JSONException;

    List<ElasticSearchQuery> toQuery();

    int count();
}