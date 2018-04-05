package com.spheremall.core.entities.shop;

import com.spheremall.core.entities.Entity;
import com.spheremall.core.jsonapi.annotations.Type;

@Type("deliveryPaymentRelations")
public class DeliveryPaymentRelation extends Entity {
    public int deliveryProviderId;
    public int paymentMethodId;
}
