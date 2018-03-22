package com.spheremall.core.filters.grid;

public class AttributeFilter extends IdentificatorFilter {

    public AttributeFilter(Integer... values) {
        super(values);
        this.name = "attributes";
    }
}
