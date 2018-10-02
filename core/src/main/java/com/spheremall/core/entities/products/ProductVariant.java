package com.spheremall.core.entities.products;

import com.spheremall.core.entities.Entity;
import com.spheremall.core.jsonapi.annotations.Type;

@Type("productVariants")
public class ProductVariant extends Entity {
    public int productId;
    public String relationId;
    public int orderNumber;
}
