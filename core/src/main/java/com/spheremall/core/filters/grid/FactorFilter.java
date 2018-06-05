package com.spheremall.core.filters.grid;

import java.util.Objects;

public class FactorFilter extends IdentificatorFilter {

    public FactorFilter(Integer... values) {
        super(values);
        this.name = "factors";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FactorFilter)) return false;
        FactorFilter that = (FactorFilter) o;
        return Objects.equals(values, that.values);
    }
}
