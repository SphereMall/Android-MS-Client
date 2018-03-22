package com.spheremall.core.filters.grid;

public class FunctionalNameFilter extends IdentificatorFilter {

    public FunctionalNameFilter(Integer... values) {
        super(values);
        this.name = "functionalNames";
    }
}
