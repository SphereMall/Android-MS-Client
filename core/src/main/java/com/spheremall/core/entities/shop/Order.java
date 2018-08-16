package com.spheremall.core.entities.shop;

import com.spheremall.core.entities.Entity;
import com.spheremall.core.jsonapi.RelType;
import com.spheremall.core.jsonapi.annotations.Relationship;
import com.spheremall.core.jsonapi.annotations.Type;

import java.util.List;

@Type("orders")
public class Order extends Entity {

    public String orderId;
    public int userId;
    public int statusId;
    public int paymentStatusId;
    public int paymentId;
    public int itemsAmount;
    public int deliveryProviderId;
    public int paymentMethodId;
    public int shippingAddressId;
    public int billingAddressId;
    public int deliveryStatusId;
    public int currency;
    public int deliveryCost;
    public int subTotalVatPrice;
    public int totalVatPrice;
    public int subTotalPrice;
    public int totalPrice;
    public int totalPriceWithoutDelivery;
    public String deliveryTime;
    public String additionalInfo;
    public String orderComment;
    public String createdDate;
    public String updateDate;

    @Relationship(value = "items", resolve = true, relType = RelType.RELATED)
    public List<OrderItem> items;
}
