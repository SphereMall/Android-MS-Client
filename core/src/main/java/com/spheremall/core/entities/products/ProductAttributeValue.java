package com.spheremall.core.entities.products;

import com.spheremall.core.entities.Entity;
import com.spheremall.core.jsonapi.RelType;
import com.spheremall.core.jsonapi.annotations.Relationship;
import com.spheremall.core.jsonapi.annotations.Type;

import java.util.List;

@Type("productAttributeValues")
public class ProductAttributeValue extends Entity {
    public int productId;
    public int attributeId;
    public String value;
    public String code;
    public String title;
    public String lastUpdate;
    public String orderNumber;

    @Relationship(value = "attributeValues", resolve = true, relType = RelType.RELATED)
    public List<AttributeValue> attributeValues;

    @Relationship(value = "attributes", resolve = true, relType = RelType.RELATED)
    public List<Attribute> attributes;
}
