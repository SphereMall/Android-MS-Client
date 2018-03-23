package com.spheremall.core.filters.grid;

public class PriceRangeFilter extends IdentificatorFilter {

    public PriceRangeFilter(Integer... values) {
        super(values);
        this.name = "priceRange";
    }
}
