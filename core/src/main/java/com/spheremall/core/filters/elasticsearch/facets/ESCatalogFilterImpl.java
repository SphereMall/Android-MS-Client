package com.spheremall.core.filters.elasticsearch.facets;

import com.spheremall.core.exceptions.SphereMallException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ESCatalogFilterImpl implements ESCatalogFilter {

    private final Map<String, ESCatalogFilterCriteria> queryParams = new HashMap<>();

    private ESCatalogFilterImpl(Builder builder) {
        //TODO: save builded config
    }

    public static ESCatalogFilter newInstance(Builder builder) {
        return new ESCatalogFilterImpl(builder);
    }

    @Override
    public ESCatalogFilter add(String key, ESCatalogFilterCriteria criteria) {
        queryParams.put(key, criteria);
        return this;
    }

    @Override
    public ESCatalogFilter remove(String key, ESCatalogFilterCriteria criteria) {
        queryParams.remove(key);
        return this;
    }

    @Override
    public JSONArray asJson() throws SphereMallException, JSONException {
        JSONArray params = new JSONArray();

        Map<String, JSONObject> criteriaJson = new HashMap<>();

        for (Map.Entry<String, ESCatalogFilterCriteria> entry : queryParams.entrySet()) {

            if (criteriaJson.containsKey(entry.getValue().name())) {
                criteriaJson.get(entry.getValue().name()).put(entry.getKey(), entry.getValue().toJson());
            } else {
                JSONObject entryObject = new JSONObject();
                entryObject.put(entry.getKey(), entry.getValue().toJson());
                criteriaJson.put(entry.getValue().name(), entryObject);
            }
            params.put(criteriaJson);
        }

        return params;
    }

    public static class Builder {

        private Map<String, ESCatalogFilterCriteria> configParams = new HashMap<>();

        public Builder setConfigCriteria(ESCatalogFilterCriteria configCriteria) {
            configParams.put(configCriteria.getClass().getSimpleName(), configCriteria);
            return this;
        }

        public JSONObject build() {
            //TODO: build config
            throw new RuntimeException();
        }
    }
}