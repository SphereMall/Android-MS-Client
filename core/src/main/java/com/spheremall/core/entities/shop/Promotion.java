package com.spheremall.core.entities.shop;

import com.spheremall.core.entities.Entity;
import com.spheremall.core.jsonapi.annotations.Type;

@Type("promotions")
public class Promotion extends Entity {
    public String title;
    public String startDate;
    public String endDate;
    public String description;
    public int active;
    public String lastUpdate;
    public String couponsTypeId;
    public String activateByCoupon;
    public String usageLimit;
    public String usagePerUser;
    public int objectId;
    public String showLabel;
}
