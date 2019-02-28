package com.spheremall.core.mappers.entityMappers;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.spheremall.core.entities.products.Media;

public class MediaMapper extends ObjectMapper<Media> {
    @Override
    public Media doObject(JsonObject obj, JsonObject relations, JsonArray includes) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        Media media = new Media();
        media.objectId = JsonObjectUtils.getInt(obj, "objectId");
        media.title = JsonObjectUtils.getString(obj, "title");
        media.imageName = JsonObjectUtils.getString(obj, "imageName");
        media.orderNumber = JsonObjectUtils.getInt(obj, "orderNumber");
        media.mediaTypeId = JsonObjectUtils.getInt(obj, "mediaTypeId");
        media.visible = JsonObjectUtils.getInt(obj, "visible");
        media.path = JsonObjectUtils.getString(obj, "path");
        media.createDate = JsonObjectUtils.getString(obj, "createDate");
        media.lastUpdate = JsonObjectUtils.getString(obj, "lastUpdate");
        media.mediaDisplayTypeId = JsonObjectUtils.getInt(obj, "mediaDisplayTypeId");
        return media;
    }
}
