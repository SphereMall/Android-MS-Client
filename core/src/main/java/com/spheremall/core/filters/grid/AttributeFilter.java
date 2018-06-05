package com.spheremall.core.filters.grid;

import java.util.Objects;

public class AttributeFilter extends IdentificatorFilter {

    public AttributeFilter(Integer... values) {
        super(values);
        this.name = "attributes";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AttributeFilter)) return false;
        AttributeFilter that = (AttributeFilter) o;
        return Objects.equals(values, that.values);
    }
}
