package com.spheremall.core.entities.products;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.spheremall.core.entities.Entity;
import com.spheremall.core.entities.price.ProductPriceConfiguration;
import com.spheremall.core.entities.shop.ProductsToPromotions;
import com.spheremall.core.entities.shop.Promotion;
import com.spheremall.core.jsonapi.RelType;
import com.spheremall.core.jsonapi.annotations.Relationship;
import com.spheremall.core.jsonapi.annotations.Type;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Type("products")
public class Product extends Entity {

    public String urlCode;
    public String shortDescription;
    public String fullDescription;
    public int visible;
    public int purchasePrice;
    public int price;
    public int oldPrice;
    public String title;

    @Relationship(value = "productPriceConfigurations", resolve = true, relType = RelType.RELATED)
    public List<ProductPriceConfiguration> productPriceConfigurations;

    @Relationship(value = "brands", resolve = true, relType = RelType.RELATED)
    public List<Brand> brands;

    @Relationship(value = "functionalNames", resolve = true, relType = RelType.RELATED)
    public List<FunctionalName> functionalNames;

    @Relationship(value = "productAttributeValues", resolve = true, relType = RelType.RELATED)
    public List<ProductAttributeValue> productAttributeValues;

    @Relationship(value = "attributes", resolve = true, relType = RelType.RELATED)
    public List<Attribute> attributes;

    @Relationship(value = "media")
    @JsonProperty("media")
    public List<Media> images;

    @Relationship(value = "options", resolve = true, relType = RelType.RELATED)
    public List<Option> options;

    @Relationship(value = "productsToPromotions", resolve = true, relType = RelType.RELATED)
    public List<ProductsToPromotions> productsToPromotions;

    @Relationship(value = "promotions", resolve = true, relType = RelType.RELATED)
    public List<Promotion> promotions;

    public List<Attribute> affectedAttributes = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return visible == product.visible &&
                purchasePrice == product.purchasePrice &&
                price == product.price &&
                oldPrice == product.oldPrice &&
                Objects.equals(urlCode, product.urlCode) &&
                Objects.equals(shortDescription, product.shortDescription) &&
                Objects.equals(fullDescription, product.fullDescription) &&
                Objects.equals(title, product.title) &&
                Objects.equals(brands, product.brands) &&
                Objects.equals(functionalNames, product.functionalNames) &&
                Objects.equals(productAttributeValues, product.productAttributeValues) &&
                Objects.equals(images, product.images) &&
                Objects.equals(options, product.options) &&
                Objects.equals(productsToPromotions, product.productsToPromotions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(urlCode, shortDescription, fullDescription, visible, purchasePrice, price, oldPrice, title, brands, functionalNames, productAttributeValues, images, options, productsToPromotions);
    }
}
