package com.spheremall.core.filters.elasticsearch.facets;

import com.spheremall.core.exceptions.SphereMallException;
import com.spheremall.core.filters.elasticsearch.common.ElasticSearchQuery;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public interface ESCatalogFilterCriteria {
    String name();

    ESCatalogFilterCriteria update(ESCatalogFilterCriteria newCriteria);

    JSONObject toJson() throws SphereMallException, JSONException;

    List<ElasticSearchQuery> toQuery();
}