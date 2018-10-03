package com.spheremall.core.entities.documents;

import com.spheremall.core.entities.Entity;
import com.spheremall.core.jsonapi.annotations.Type;

@Type("entityAttributeValues")
public class EntityAttributeValue extends Entity {
    public int entityId;
    public int objectId;
    public int attributeId;
    public int attributeValueId;
    public String lastUpdate;

}
