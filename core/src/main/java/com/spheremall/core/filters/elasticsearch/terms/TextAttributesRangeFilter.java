package com.spheremall.core.filters.elasticsearch.terms;

public class TextAttributesRangeFilter extends RangeFilter<String> {

    public TextAttributesRangeFilter(String attribute) {
        super(attribute);
    }
}
