package com.spheremall.core.entities.products;

import com.google.gson.annotations.SerializedName;
import com.spheremall.core.entities.Entity;
import com.spheremall.core.jsonapi.annotations.Type;

@Type("imageTypes")
public class MediaType extends Entity {

    public int orderNumber;
    public String title;
    public String description;
    @SerializedName("class")
    public String clazz;
}
