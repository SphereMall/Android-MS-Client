package com.spheremall.core.filters.grid;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class BrandFilter extends IdentificatorFilter {

    public BrandFilter(Integer... values) {
        this.name = "brands";
        this.values = Arrays.asList(values);
    }

    public List<Integer> getValues() {
        return values;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BrandFilter)) return false;
        BrandFilter that = (BrandFilter) o;
        return Objects.equals(values, that.values);
    }
}
