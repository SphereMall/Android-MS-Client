package com.spheremall.core.entities.documents;

import com.spheremall.core.entities.Entity;
import com.spheremall.core.jsonapi.annotations.Type;

@Type("entitiesAverageRating")
public class EntityAverageRating extends Entity {
    public int entityId;
    public int objectId;
    public double averageRating;
    public String lastUpdate;
}
