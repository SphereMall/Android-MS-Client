package com.spheremall.core.entities.products;

import com.spheremall.core.entities.Entity;
import com.spheremall.core.jsonapi.RelType;
import com.spheremall.core.jsonapi.annotations.Relationship;
import com.spheremall.core.jsonapi.annotations.Type;

import java.util.ArrayList;
import java.util.List;

@Type("attributes")
public class Attribute extends Entity implements Cloneable {

    public int showInSpecList;
    public String code;
    public String title;
    public String description;
    public int attributeGroupId;
    public String cssClass;
    public int orderNumber;
    public int useInFilter;

    @Relationship(value = "attributeValues", resolve = true, relType = RelType.RELATED)
    public ArrayList<AttributeValue> attributeValues;

    @Relationship(value = "attributeGroups", resolve = true, relType = RelType.RELATED)
    public AttributeGroup group;

    @Relationship(value = "attributeTypes", resolve = true, relType = RelType.RELATED)
    public List<AttributeType> attributeTypes;

    @Override
    public Attribute clone() throws CloneNotSupportedException {
        return (Attribute) super.clone();
    }
}
