package com.spheremall.core.entities.products;

import com.spheremall.core.jsonapi.annotations.Type;
import com.spheremall.core.entities.Entity;

@Type("entityattributes")
public class EntityAttribute extends Entity {
    public int entityId;
    public int objectId;
    public int attributeId;
    public int attributeValueId;
    public String lastUpdate;
}
