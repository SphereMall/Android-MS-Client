package com.spheremall.core.entities;

public class PriceRange {
    public final int minPrice;
    public final int maxPrice;
    public final int amounts;

    public PriceRange(int minPrice, int maxPrice, int amounts) {
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.amounts = amounts;
    }
}
