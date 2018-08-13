package com.spheremall.core.entities.pojo;

import com.spheremall.core.entities.Entity;
import com.spheremall.core.jsonapi.annotations.Type;

@Type("priceValues")
public class PriceValuePojo extends Entity {

    public int productId;
    public Price prices;
    public double vatPercent;

    public static class Price {
        public double priceWithVat;
        public double priceWithoutVat;
    }
}
