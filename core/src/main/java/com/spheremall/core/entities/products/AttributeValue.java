package com.spheremall.core.entities.products;

import com.spheremall.core.jsonapi.annotations.Type;
import com.spheremall.core.entities.Entity;

@Type("attributeValues")
public class AttributeValue extends Entity implements Cloneable {

    public String value;
    public int attributeId;
    public String code;
    public String title;
    public String image;
    public int orderNumber;
    public int amount;
    public int showInSpecList;

    @Override
    public AttributeValue clone() throws CloneNotSupportedException {
        return (AttributeValue) super.clone();
    }
}
