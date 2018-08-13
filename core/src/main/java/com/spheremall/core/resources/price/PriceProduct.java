package com.spheremall.core.resources.price;

import com.spheremall.core.entities.Entity;
import com.spheremall.core.entities.products.AttributeValue;
import com.spheremall.core.entities.products.Option;

import java.util.List;

public class PriceProduct extends Entity {

    public int productId;
    public int priceTypeId;
    public List<AttributeValue> attributes;
    public List<Option> options;

    public PriceProduct(int productId, int priceTypeId, List<AttributeValue> attributes, List<Option> options) {
        this.productId = productId;
        this.priceTypeId = priceTypeId;
        this.attributes = attributes;
        this.options = options;
    }
}
