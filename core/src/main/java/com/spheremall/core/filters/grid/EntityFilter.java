package com.spheremall.core.filters.grid;

import java.util.List;
import java.util.Objects;

public class EntityFilter extends StringGridFilter {

    public EntityFilter(String... values) {
        super(values);
        this.name = "entity";
    }

    @Override
    public List<String> getValues() {
        return values;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EntityFilter)) return false;
        if (!super.equals(o)) return false;
        EntityFilter that = (EntityFilter) o;
        return Objects.equals(values, that.values);
    }
}
