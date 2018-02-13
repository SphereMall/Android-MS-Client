package com.spheremall.core.shop;

import com.spheremall.core.entities.shop.DeliveryProvider;

public class Delivery {

    public int id;
    protected DeliveryProvider deliveryProvider;

    public Delivery(DeliveryProvider deliveryProvider) {
        this.deliveryProvider = deliveryProvider;
        this.id = deliveryProvider.getId();
    }

    public int getCost() {
        return this.deliveryProvider.cost;
    }

    public String getName() {
        return this.deliveryProvider.name;
    }
}
