package com.spheremall.core.entities.products;

import com.spheremall.core.entities.Entity;
import com.spheremall.core.jsonapi.annotations.Type;

@Type("mediaEntities")
public class MediaEntity extends Entity {

    public int entityId;
    public int objectId;
    public int mediaId;
    public String title;
    public int orderNumber;
    public int mediaDisplayTypeId;
}
