package com.spheremall.core.entities.products;

import com.spheremall.core.entities.Entity;
import com.spheremall.core.jsonapi.annotations.Type;

@Type("attributeGroupsEntities")
public class AttributeGroupsEntities extends Entity {

    private int entityId;
    private int attributeGroupsId;
    private int attributeId;

    public int getEntityId() {
        return entityId;
    }

    public void setEntityId(int entityId) {
        this.entityId = entityId;
    }

    public int getAttributeGroupsId() {
        return attributeGroupsId;
    }

    public void setAttributeGroupsId(int attributeGroupsId) {
        this.attributeGroupsId = attributeGroupsId;
    }

    public int getAttributeId() {
        return attributeId;
    }

    public void setAttributeId(int attributeId) {
        this.attributeId = attributeId;
    }
}
