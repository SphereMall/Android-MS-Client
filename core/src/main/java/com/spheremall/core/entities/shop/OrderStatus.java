package com.spheremall.core.entities.shop;

import com.spheremall.core.entities.Entity;
import com.spheremall.core.jsonapi.annotations.Type;

@Type("orderStatus")
public class OrderStatus extends Entity {

    public String description;
}
