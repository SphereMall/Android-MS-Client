package com.spheremall.core.filters.grid;

import org.json.JSONArray;

import java.util.Objects;

public class PriceRangeFilter extends IdentificatorFilter {

    public PriceRangeFilter(Integer price1, Integer price2) {
        super(price1, price2);
        this.name = "priceRange";
    }

    @Override
    public JSONArray asArray() {
        JSONArray set = new JSONArray();
        if (values != null && values.size() > 0) {
            for (Integer item : values) {
                set.put(item);
            }
        }
        JSONArray array = new JSONArray();
        return array.put(set);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PriceRangeFilter)) return false;
        PriceRangeFilter that = (PriceRangeFilter) o;
        return Objects.equals(values, that.values);
    }
}
