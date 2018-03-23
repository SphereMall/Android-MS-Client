package com.spheremall.core.entities.products;

import com.spheremall.core.entities.Entity;
import com.spheremall.core.jsonapi.RelType;
import com.spheremall.core.jsonapi.annotations.Relationship;
import com.spheremall.core.jsonapi.annotations.Type;

import java.util.List;

@Type("attributes")
public class Attribute extends Entity {

    public int showInSpecList;
    public String code;
    public String title;
    public String description;
    public int attributeGroupId;
    public String cssClass;
    public int orderNumber;
    public int useInFilter;

    @Relationship(value = "attributeValues", resolve = true, relType = RelType.RELATED)
    public List<AttributeValue> values;

    @Relationship(value = "attributeGroups", resolve = true, relType = RelType.RELATED)
    public AttributeGroup group;
}
