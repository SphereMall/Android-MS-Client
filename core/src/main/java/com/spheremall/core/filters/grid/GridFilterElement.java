package com.spheremall.core.filters.grid;

import org.json.JSONArray;

import java.util.Objects;

public abstract class GridFilterElement {
    protected String name;

    public String getName() {
        return name;
    }

    public abstract JSONArray asArray();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GridFilterElement)) return false;
        GridFilterElement that = (GridFilterElement) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name);
    }
}
