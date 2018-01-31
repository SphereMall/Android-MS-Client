package com.spheremall.core.entities.products;

import com.spheremall.core.entities.Entity;
import com.spheremall.core.jsonapi.annotations.Type;

@Type("catalogItems")
public class CatalogItem extends Entity {

    public int parentId;
    public int noindex;
    public int clickable;
    public int visible;
    public int orderNumber;
    public int openLinkInNewWindow;
    public int moduleId;

    public String treeItemName;
    public String urlCode;
    public String seoTitle;
    public String seoDescription;
    public String seoKeywords;
    public String lastUpdate;

    public String filterSettings;
    public String hideItemSettings;
}
