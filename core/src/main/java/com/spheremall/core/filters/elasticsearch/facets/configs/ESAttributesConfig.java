package com.spheremall.core.filters.elasticsearch.facets.configs;

import org.json.JSONArray;

import java.util.List;

public class ESAttributesConfig implements ESCatalogConfig {

    private final List<String> codes;

    public ESAttributesConfig(List<String> codes) {
        this.codes = codes;
    }

    @Override
    public String name() {
        return "attributes";
    }

    @Override
    public JSONArray toConfig() {
        JSONArray codesArray = new JSONArray();
        for (String code : codes) {
            codesArray.put(code);
        }
        return codesArray;
    }
}
