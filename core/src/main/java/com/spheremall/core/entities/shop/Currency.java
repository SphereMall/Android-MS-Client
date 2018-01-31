package com.spheremall.core.entities.shop;

import com.google.gson.annotations.SerializedName;
import com.spheremall.core.entities.Entity;
import com.spheremall.core.jsonapi.annotations.Type;

@Type("currencies")
public class Currency extends Entity {
    public String title;
    public String code;
    public String symbol;
    public int active;

    @SerializedName("default")
    public int defaultValue;
}
