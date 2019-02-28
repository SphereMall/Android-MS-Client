package com.spheremall.core.mappers.entityMappers;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.spheremall.core.entities.products.Brand;

public class BrandsMapper extends ObjectMapper<Brand> {

    @Override
    public Brand doObject(JsonObject obj, JsonObject relations, JsonArray includes) {
        Brand brand = new Brand();
        brand.visible = JsonObjectUtils.getInt(obj, "visible");
        brand.urlCode = JsonObjectUtils.getString(obj, "urlCode");
        brand.title = JsonObjectUtils.getString(obj, "title");
        brand.image = JsonObjectUtils.getString(obj, "image");
        brand.shortDescription = JsonObjectUtils.getString(obj, "shortDescription");
        brand.fullDescription = JsonObjectUtils.getString(obj, "fullDescription");
        brand.logo = JsonObjectUtils.getString(obj, "logo");
        brand.orderNumber = JsonObjectUtils.getString(obj, "orderNumber");
        brand.lastUpdate = JsonObjectUtils.getString(obj, "lastUpdate");
        return brand;
    }
}
