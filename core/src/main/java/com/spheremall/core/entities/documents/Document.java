package com.spheremall.core.entities.documents;

import com.spheremall.core.entities.Entity;
import com.spheremall.core.entities.products.Attribute;
import com.spheremall.core.entities.products.AttributeValue;
import com.spheremall.core.entities.products.FunctionalName;
import com.spheremall.core.entities.products.Media;
import com.spheremall.core.entities.products.MediaDisplayType;
import com.spheremall.core.entities.products.MediaEntity;
import com.spheremall.core.jsonapi.RelType;
import com.spheremall.core.jsonapi.annotations.Relationship;
import com.spheremall.core.jsonapi.annotations.Type;

import java.util.List;

@Type("documents")
public class Document extends Entity {
    public int functionalNameId;
    public String title;
    public String urlCode;
    public String createDate;
    public String lastUpdate;
    public String visible;

    @Relationship(value = "functionalNames", resolve = true, relType = RelType.RELATED)
    public List<FunctionalName> functionalNames;

    @Relationship(value = "attributes", resolve = true, relType = RelType.RELATED)
    public List<Attribute> attributes;

    @Relationship(value = "attributeValues", resolve = true, relType = RelType.RELATED)
    public List<AttributeValue> attributeValues;

    @Relationship(value = "entityAttributeValues", resolve = true, relType = RelType.RELATED)
    public List<EntityAttributeValue> entityAttributeValues;

    @Relationship(value = "mediaEntities", resolve = true, relType = RelType.RELATED)
    public List<MediaEntity> mediaEntities;

    @Relationship(value = "media", resolve = true, relType = RelType.RELATED)
    public List<Media> media;

    @Relationship(value = "mediaDisplayTypes", resolve = true, relType = RelType.RELATED)
    public List<MediaDisplayType> mediaDisplayTypes;
}
