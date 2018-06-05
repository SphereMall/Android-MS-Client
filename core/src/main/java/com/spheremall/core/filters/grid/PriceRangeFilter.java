package com.spheremall.core.filters.grid;

import java.util.Objects;

public class PriceRangeFilter extends IdentificatorFilter {

    public PriceRangeFilter(Integer... values) {
        super(values);
        this.name = "priceRange";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PriceRangeFilter)) return false;
        PriceRangeFilter that = (PriceRangeFilter) o;
        return Objects.equals(values, that.values);
    }
}
