package com.spheremall.core.filters.elasticsearch.facets;

import com.spheremall.core.exceptions.SphereMallException;

import org.json.JSONArray;
import org.json.JSONException;

public interface ESCatalogFilter {

    ESCatalogFilter add(String key, ESCatalogFilterCriteria criteria);

    ESCatalogFilter remove(String key, ESCatalogFilterCriteria criteria);

    JSONArray asJson() throws SphereMallException, JSONException;
}