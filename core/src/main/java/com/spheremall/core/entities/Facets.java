package com.spheremall.core.entities;

import com.spheremall.core.entities.products.Attribute;

import java.util.List;

public class Facets extends Entity {
    public final List<Attribute> attributes;
    public final PriceRange priceRanges;

    public Facets(List<Attribute> attributes, PriceRange priceRanges) {
        this.attributes = attributes;
        this.priceRanges = priceRanges;
    }
}
