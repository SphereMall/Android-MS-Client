package com.spheremall.core.entities.price;

import com.spheremall.core.entities.Entity;

public class PriceValue extends Entity {

    public int productId;
    public double priceWithVat;
    public double priceWithoutVat;
    public double vatPercent;

}
