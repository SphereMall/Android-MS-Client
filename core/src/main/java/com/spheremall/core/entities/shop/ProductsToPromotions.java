package com.spheremall.core.entities.shop;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.spheremall.core.entities.Entity;
import com.spheremall.core.jsonapi.annotations.Type;

@Type("productsToPromotions")
public class ProductsToPromotions extends Entity {
    public int productId;
    public int promotionId;
    public int ruleId;
    public int discountTypeId;

    public double discountValue;
    public double price;
    public double itemPrice;
    public double discountPrice;

    public String title;
    public String discountTypeTitle;
    public String showLabel;

    @JsonProperty("class")
    public String clazz;
}
