package com.spheremall.core.filters.elasticsearch.terms;

public class PriceRangeFilter extends RangeFilter<Double> {

    public PriceRangeFilter(String attribute) {
        super(attribute);
    }

    public void setRange(Double minPrice, Double maxPrice) {
        addOption("gt", minPrice);
        addOption("lt", maxPrice);
    }
}