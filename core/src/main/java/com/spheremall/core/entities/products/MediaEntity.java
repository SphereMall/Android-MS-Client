package com.spheremall.core.entities.products;

import com.spheremall.core.entities.Entity;
import com.spheremall.core.jsonapi.RelType;
import com.spheremall.core.jsonapi.annotations.Relationship;
import com.spheremall.core.jsonapi.annotations.Type;

import java.util.List;

@Type("mediaEntities")
public class MediaEntity extends Entity {

    public int entityId;
    public int objectId;
    public int mediaId;
    public String title;
    public int orderNumber;
    public int mediaDisplayTypeId;

    @Relationship(value = "mediaDisplayTypes", resolve = true, relType = RelType.RELATED)
    public List<MediaDisplayType> mediaDisplayTypes;

    @Relationship(value = "media", resolve = true, relType = RelType.RELATED)
    public List<Media> media;
}
