package com.spheremall.core.entities.shop;

import com.spheremall.core.entities.Entity;
import com.spheremall.core.jsonapi.annotations.Type;

@Type("deliveryProviders")
public class DeliveryProvider extends Entity {
    public String name;
    public int cost;
    public int visible;
}
