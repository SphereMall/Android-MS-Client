package com.spheremall.core.filters.grid;

import org.json.JSONArray;

import java.util.Arrays;
import java.util.List;

public abstract class StringGridFilter extends GridFilterElement {
    protected List<String> values;

    public StringGridFilter(String... values) {
        this.values = Arrays.asList(values);
    }

    public List<String> getValues() {
        return values;
    }

    @Override
    public JSONArray asArray() {
        JSONArray set = new JSONArray();
        if (values != null && values.size() > 0) {
            for (String item : values) {
                set.put(item);
            }
        }
        return set;
    }
}
