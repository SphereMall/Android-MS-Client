package com.spheremall.core.entities.products;

import com.spheremall.core.entities.Entity;
import com.spheremall.core.jsonapi.RelType;
import com.spheremall.core.jsonapi.annotations.Relationship;
import com.spheremall.core.jsonapi.annotations.Type;

import java.util.List;

@Type("products")
public class Product extends Entity {

    public String urlCode;
    public String shortDescription;
    public String fullDescription;
    public String seoDescription;
    public String seoKeywords;
    public int visible;
    public int purchasePrice;
    public int price;
    public int oldPrice;
    public String importedId;
    public String variantsCompound;
    public String title;

    @Relationship(value = "brands", resolve = true, relType = RelType.RELATED)
    public List<Brand> brands;

    @Relationship(value = "functionalNames", resolve = true, relType = RelType.RELATED)
    public List<FunctionalName> functionalNames;

    @Relationship(value = "productAttributeValues", resolve = true, relType = RelType.RELATED)
    public List<ProductAttributeValue> productAttributeValues;

    @Relationship(value = "images", resolve = true, relType = RelType.RELATED)
    public List<Media> images;


    @Relationship(value = "options", resolve = true, relType = RelType.RELATED)
    public List<Option> options;
}
