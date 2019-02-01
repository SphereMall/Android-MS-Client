package com.spheremall.core.filters.elasticsearch.facets.configs;

public class ESBrandsConfig implements ESCatalogConfig {

    @Override
    public String name() {
        return "brands";
    }

    @Override
    public String toConfig() {
        return "true";
    }
}
