package com.spheremall.core.entities.shop;

import com.spheremall.core.entities.Entity;
import com.spheremall.core.entities.products.Product;
import com.spheremall.core.jsonapi.annotations.Type;

import java.util.List;

@Type("orderItems")
public class OrderItem extends Entity {

    public int orderId;
    public int amount;
    public int productId;
    public int itemPrice;
    public int itemDiscountPrice;
    public int itemPriceWithDiscount;
    public int vatId;
    public int itemVatPrice;
    public int itemVatExcludePrice;
    public int totalPrice;

    public List<Product> products;
    public String compound;
}
