package com.spheremall.core.filters.elasticsearch.facets.configs;

public class ESPriceRangeConfig implements ESCatalogConfig {
    @Override
    public String name() {
        return "priceRange";
    }

    @Override
    public String toConfig() {
        return "true";
    }
}
