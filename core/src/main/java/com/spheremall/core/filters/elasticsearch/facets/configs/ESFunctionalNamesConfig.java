package com.spheremall.core.filters.elasticsearch.facets.configs;

public class ESFunctionalNamesConfig implements ESCatalogConfig {

    @Override
    public String name() {
        return "functionalNames";
    }

    @Override
    public String toConfig() {
        return "true";
    }
}
