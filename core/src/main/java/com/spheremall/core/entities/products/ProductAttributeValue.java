package com.spheremall.core.entities.products;

import com.spheremall.core.entities.Entity;
import com.spheremall.core.jsonapi.annotations.Type;

@Type("productAttributeValues")
public class ProductAttributeValue extends Entity {
    public int productId;
    public int productAttributeValueId;
    public int attributeId;
    public String value;
    public String valueTitle;
    public String code;
    public String title;
    public String lastUpdate;
    public String orderNumber;
    public int showInSpecList;
    public String cssClass;
    public int useInFilter;
}
