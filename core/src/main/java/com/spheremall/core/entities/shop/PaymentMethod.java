package com.spheremall.core.entities.shop;

import com.spheremall.core.entities.Entity;
import com.spheremall.core.jsonapi.annotations.Type;

@Type("paymentMethods")
public class PaymentMethod extends Entity {
    public String title;
    public String code;
    public String icon;
    public int active;
}
