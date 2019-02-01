package com.spheremall.core.filters.elasticsearch.facets;

import com.spheremall.core.exceptions.SphereMallException;
import com.spheremall.core.filters.elasticsearch.common.ElasticSearchFilter;
import com.spheremall.core.filters.elasticsearch.common.ElasticSearchQuery;
import com.spheremall.core.filters.elasticsearch.compound.BoolFilter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public interface ESCatalogFilter {

    ESCatalogFilter add(ESCatalogFilterCriteria criteria);

    ESCatalogFilter remove(ESCatalogFilterCriteria criteria);

    JSONArray toParams() throws SphereMallException, JSONException;

    JSONObject toConfig() throws SphereMallException, JSONException;

    BoolFilter toBoolFilter();
}