package com.spheremall.core.filters.grid;

import org.json.JSONArray;

public abstract class GridFilterElement {
    protected String name;

    public String getName() {
        return name;
    }

    public abstract JSONArray asArray();
}
