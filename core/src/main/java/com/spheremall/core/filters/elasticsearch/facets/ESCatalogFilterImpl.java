package com.spheremall.core.filters.elasticsearch.facets;

import com.spheremall.core.filters.elasticsearch.common.ElasticSearchQuery;
import com.spheremall.core.filters.elasticsearch.compound.BoolFilter;
import com.spheremall.core.filters.elasticsearch.facets.configs.ESCatalogConfig;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ESCatalogFilterImpl implements ESCatalogFilter {

    private final List<ESCatalogConfig> configParams;
    private final Map<String, ESCatalogFilterCriteria> queryParams = new HashMap<>();

    public ESCatalogFilterImpl(List<ESCatalogConfig> configParams) {
        this.configParams = configParams;
    }

    @Override
    public ESCatalogFilter add(ESCatalogFilterCriteria criteria) {
        if (queryParams.containsKey(criteria.name())) {
            ESCatalogFilterCriteria savedCriteria = queryParams.get(criteria.name());
            queryParams.put(criteria.name(), savedCriteria.update(criteria));
        } else {
            queryParams.put(criteria.name(), criteria);
        }
        return this;
    }

    @Override
    public ESCatalogFilter remove(ESCatalogFilterCriteria criteria) {
        queryParams.remove(criteria.name());
        return this;
    }

    @Override
    public JSONArray toParams() throws JSONException {
        JSONArray params = new JSONArray();
        JSONObject paramsObject = new JSONObject();

        for (Map.Entry<String, ESCatalogFilterCriteria> entry : queryParams.entrySet()) {
            JSONObject entryObject = entry.getValue().toJson(configParams);
            Object object = entryObject.get(entry.getKey());
            if (object instanceof JSONObject) {
                JSONObject criteriaObject = entryObject.getJSONObject(entry.getKey());
                paramsObject.put(entry.getKey(), criteriaObject);
            } else {
                JSONArray criteriaArray = entryObject.getJSONArray(entry.getKey());
                paramsObject.put(entry.getKey(), criteriaArray);
            }
        }
        if (!paramsObject.keys().hasNext()) return params;

        return params.put(paramsObject);
    }

    @Override
    public JSONObject toConfig() throws JSONException {
        JSONObject configObject = new JSONObject();
        for (ESCatalogConfig config : configParams) {
            configObject.put(config.name(), config.toConfig());
        }
        return configObject;
    }

    @Override
    public BoolFilter toBoolFilter() {
        BoolFilter boolFilter = new BoolFilter();
        boolean isQueryAdded = false;

        for (Map.Entry<String, ESCatalogFilterCriteria> entry : queryParams.entrySet()) {
            ElasticSearchQuery[] items = new ElasticSearchQuery[entry.getValue().toQuery().size()];
            boolFilter.must(entry.getValue().toQuery().toArray(items));
            isQueryAdded = true;
        }
        if (!isQueryAdded) return null;

        return boolFilter;
    }

    @Override
    public String hash() {
        BoolFilter boolFilter = toBoolFilter();
        if (boolFilter == null) return null;

        return String.valueOf(
                boolFilter.toJson()
                        .toString().hashCode());
    }

    @Override
    public int count() {
        int amount = 0;
        for (Map.Entry<String, ESCatalogFilterCriteria> entry : queryParams.entrySet()) {
            amount += entry.getValue().count();
        }
        return amount;
    }

    @Override
    public Map<String, ESCatalogFilterCriteria> listOfCriteria() {
        return queryParams;
    }
}