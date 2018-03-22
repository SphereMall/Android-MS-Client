package com.spheremall.core.filters.grid;

import java.util.List;

public class EntityFilter extends StringGridFilter {

    public EntityFilter(String... values) {
        super(values);
        this.name = "entity";
    }

    @Override
    public List<String> getValues() {
        return values;
    }
}
