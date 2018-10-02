package com.spheremall.core.entities.products;

import com.spheremall.core.entities.Entity;
import com.spheremall.core.jsonapi.annotations.Type;

@Type("mediaDisplayTypes")
public class MediaDisplayType extends Entity {

    public String title;
    public String description;
    public String cssClass;
    public int orderNumber;
    public String lastUpdate;
}
