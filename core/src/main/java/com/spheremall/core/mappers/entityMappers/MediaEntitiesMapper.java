package com.spheremall.core.mappers.entityMappers;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.spheremall.core.entities.products.MediaEntity;

public class MediaEntitiesMapper extends ObjectMapper<MediaEntity> {

    @Override
    public MediaEntity doObject(JsonObject obj, JsonObject relations, JsonArray includes) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        MediaEntity media = new MediaEntity();
        media.objectId = JsonObjectUtils.getInt(obj, "objectId");
        media.title = JsonObjectUtils.getString(obj, "title");
        media.entityId = JsonObjectUtils.getInt(obj, "entityId");
        media.mediaId = JsonObjectUtils.getInt(obj, "mediaId");
        media.title = JsonObjectUtils.getString(obj, "title");
        media.orderNumber = JsonObjectUtils.getInt(obj, "orderNumber");
        media.mediaDisplayTypeId = JsonObjectUtils.getInt(obj, "mediaDisplayTypeId");
        return media;
    }
}
