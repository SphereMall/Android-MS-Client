package com.spheremall.core.entities.products;

import com.spheremall.core.entities.Entity;
import com.spheremall.core.jsonapi.annotations.Type;

@Type("brands")
public class Brand extends Entity {

    public int visible;
    public String urlCode;
    public String title;
    public String image;
    public String shortDescription;
    public String fullDescription;
    public String seoTitle;
    public String seoDescription;
    public String seoKeywords;
    public String orderNumber;
    public String lastUpdate;
}
