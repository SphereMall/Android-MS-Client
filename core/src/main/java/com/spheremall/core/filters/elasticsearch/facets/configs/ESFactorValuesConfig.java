package com.spheremall.core.filters.elasticsearch.facets.configs;

import org.json.JSONArray;

import java.util.List;

public class ESFactorValuesConfig implements ESCatalogConfig {

    private final List<Integer> factorValueIds;

    public ESFactorValuesConfig(List<Integer> factorValueIds) {
        this.factorValueIds = factorValueIds;
    }

    @Override
    public String name() {
        return "factorValues";
    }

    @Override
    public JSONArray toConfig() {
        JSONArray factorValuesArray = new JSONArray();
        for (Integer id : factorValueIds) {
            factorValuesArray.put(id);
        }
        return factorValuesArray;
    }
}
