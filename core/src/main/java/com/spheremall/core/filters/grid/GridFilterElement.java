package com.spheremall.core.filters.grid;

import java.util.List;

public class GridFilterElement {
    protected String name;
    protected List<Class> values;

    public GridFilterElement(List<Class> values) {
        this.values = values;
    }

    public String getName() {
        return name;
    }

    public List<Class> getValues() {
        return values;
    }
}
