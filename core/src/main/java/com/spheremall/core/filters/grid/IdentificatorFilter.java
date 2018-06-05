package com.spheremall.core.filters.grid;

import org.json.JSONArray;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public abstract class IdentificatorFilter extends GridFilterElement {
    protected List<Integer> values;

    public IdentificatorFilter(Integer... values) {
        this.values = Arrays.asList(values);
    }

    public List<Integer> getValues() {
        return values;
    }

    @Override
    public JSONArray asArray() {
        JSONArray set = new JSONArray();
        if (values != null && values.size() > 0) {
            for (Integer item : values) {
                set.put(item);
            }
        }
        return set;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IdentificatorFilter)) return false;
        IdentificatorFilter that = (IdentificatorFilter) o;
        return Objects.equals(values, that.values);
    }

    @Override
    public int hashCode() {
        return Objects.hash(values);
    }
}
