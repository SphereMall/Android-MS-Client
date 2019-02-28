package com.spheremall.core.mappers.entityMappers;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.spheremall.core.entities.Entity;
import com.spheremall.core.entities.products.Attribute;
import com.spheremall.core.entities.products.AttributeValue;
import com.spheremall.core.entities.products.Brand;
import com.spheremall.core.entities.products.MediaEntity;
import com.spheremall.core.entities.products.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProductsMapper extends ObjectMapper<Product> {

    @Override
    public Product doObject(JsonObject obj, JsonObject relations, JsonArray includes) {
        Product product = new Product();
        product.setId(JsonObjectUtils.getInt(obj, "id"));
        product.urlCode = JsonObjectUtils.getString(obj, "urlCode");
        product.shortDescription = JsonObjectUtils.getString(obj, "shortDescription");
        product.fullDescription = JsonObjectUtils.getString(obj, "fullDescription");
        product.visible = JsonObjectUtils.getInt(obj, "visible");
        product.price = JsonObjectUtils.getInt(obj, "price");
        product.oldPrice = JsonObjectUtils.getInt(obj, "oldPrice");
        product.title = JsonObjectUtils.getString(obj, "title");
        product.disable = JsonObjectUtils.getInt(obj, "disable");
        product.isMain = JsonObjectUtils.getInt(obj, "isMain");
        product.rating = JsonObjectUtils.getDouble(obj, "rating");
        product.priceTypeId = JsonObjectUtils.getInt(obj, "priceTypeId");
        product.variantsCompound = JsonObjectUtils.getString(obj, "variantsCompound");
        product.vatId = JsonObjectUtils.getInt(obj, "vatId");
        product.orderNumber = JsonObjectUtils.getInt(obj, "orderNumber");

        if (relations == null || includes == null) return product;

        Map<String, List<Entity>> relatedEntities = getRelations(relations, includes);
        if (relatedEntities.containsKey("brands")) {
            List<Brand> brands = new ArrayList<>();
            for (Entity entity : relatedEntities.get("brands")) {
                brands.add((Brand) entity);
            }
            product.brands = new ArrayList<>();
            product.brands.addAll(brands);
        }

        if (relatedEntities.containsKey("mediaEntities")) {
            List<MediaEntity> mediaEntities = new ArrayList<>();
            for (Entity entity : relatedEntities.get("mediaEntities")) {
                mediaEntities.add((MediaEntity) entity);
            }
            product.mediaEntities = new ArrayList<>();
            product.mediaEntities.addAll(mediaEntities);
        }
        if (relatedEntities.containsKey("attributes")) {
            List<Attribute> attributes = new ArrayList<>();
            for (Entity entity : relatedEntities.get("attributes")) {
                attributes.add((Attribute) entity);
            }
            product.attributes = new ArrayList<>();
            product.attributes.addAll(attributes);
        }

        if (relatedEntities.containsKey("attributeValues")) {
            List<AttributeValue> attributeValues = new ArrayList<>();
            for (Entity entity : relatedEntities.get("attributeValues")) {
                attributeValues.add((AttributeValue) entity);
            }
            product.attributeValues = new ArrayList<>();
            product.attributeValues.addAll(attributeValues);
        }


        return product;
    }
}
