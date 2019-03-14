package com.spheremall.core.filters.elasticsearch.facets;

import com.spheremall.core.exceptions.SphereMallException;
import com.spheremall.core.filters.elasticsearch.compound.BoolFilter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

public interface ESCatalogFilter {

    ESCatalogFilter add(ESCatalogFilterCriteria criteria);

    ESCatalogFilter remove(ESCatalogFilterCriteria criteria);

    JSONArray toParams() throws JSONException;

    JSONObject toConfig() throws SphereMallException, JSONException;

    BoolFilter toBoolFilter();

    String hash();

    int count();

    Map<String, ESCatalogFilterCriteria> listOfCriteria();
}