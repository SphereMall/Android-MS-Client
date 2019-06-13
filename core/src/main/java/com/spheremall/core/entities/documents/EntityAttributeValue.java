package com.spheremall.core.entities.documents;

import com.spheremall.core.entities.Entity;
import com.spheremall.core.entities.products.Attribute;
import com.spheremall.core.entities.products.AttributeValue;
import com.spheremall.core.jsonapi.RelType;
import com.spheremall.core.jsonapi.annotations.Relationship;
import com.spheremall.core.jsonapi.annotations.Type;

import java.util.List;

@Type("entityAttributeValues")
public class EntityAttributeValue extends Entity {
    public int entityId;
    public int objectId;
    public int attributeId;
    public int attributeValueId;
    public String lastUpdate;

    @Relationship(value = "attributeValues", resolve = true, relType = RelType.RELATED)
    public List<AttributeValue> attributeValues;

    @Relationship(value = "attributes", resolve = true, relType = RelType.RELATED)
    public List<Attribute> attributes;
}