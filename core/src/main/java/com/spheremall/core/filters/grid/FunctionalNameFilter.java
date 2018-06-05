package com.spheremall.core.filters.grid;

import java.util.Objects;

public class FunctionalNameFilter extends IdentificatorFilter {

    public FunctionalNameFilter(Integer... values) {
        super(values);
        this.name = "functionalNames";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FunctionalNameFilter)) return false;
        FunctionalNameFilter that = (FunctionalNameFilter) o;
        return Objects.equals(values, that.values);
    }
}
