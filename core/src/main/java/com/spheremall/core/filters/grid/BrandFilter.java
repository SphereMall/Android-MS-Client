package com.spheremall.core.filters.grid;

import java.util.Arrays;
import java.util.List;

public class BrandFilter extends IdentificatorFilter {

    public BrandFilter(Integer... values) {
        this.name = "brands";
        this.values = Arrays.asList(values);
    }

    public List<Integer> getValues() {
        return values;
    }
}
