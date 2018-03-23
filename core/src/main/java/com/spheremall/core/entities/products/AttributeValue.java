package com.spheremall.core.entities.products;

import com.spheremall.core.jsonapi.annotations.Type;
import com.spheremall.core.entities.Entity;

@Type("attributeValues")
public class AttributeValue extends Entity {

    public String value;
    public String code;
    public String cssClass;
    public String title;
    public String image;
    public int orderNumber;
    public int amount;
}
