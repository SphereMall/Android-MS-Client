package com.spheremall.core.entities.products;

import com.spheremall.core.jsonapi.RelType;
import com.spheremall.core.jsonapi.annotations.Relationship;
import com.spheremall.core.jsonapi.annotations.Type;
import com.spheremall.core.entities.Entity;

import java.util.List;

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

    @Relationship(value = "attributes", resolve = true, relType = RelType.RELATED)
    public List<Attribute> attribute;
}
