package com.spheremall.core.entities.shop;

import com.spheremall.core.jsonapi.annotations.Type;

@Type("basket")
public class BasketOrder extends Order {

//    @Relationship(value = "items", resolve = true, relType = RelType.RELATED)
//    public List<BasketItem> items;
}
