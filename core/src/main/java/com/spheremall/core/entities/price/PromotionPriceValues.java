package com.spheremall.core.entities.price;

import com.spheremall.core.entities.Entity;
import com.spheremall.core.jsonapi.annotations.Type;

@Type("promotionPriceValues")
public class PromotionPriceValues extends Entity {
    public int productId;
    public int vatId;
    public int productVatId;
    public float priceWithVat;
    public float priceWithoutVat;
    public float vatPercent;
    public PriceValue prices;
}
