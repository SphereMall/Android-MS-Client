package com.spheremall.core.entities.products;

import com.spheremall.core.jsonapi.annotations.Type;
import com.spheremall.core.entities.Entity;

@Type("media")
public class Media extends Entity {

    public int objectId;
    public String title;
    public String imageName;
    public int orderNumber;
    public int mediaTypeId;
    public int visible;
    public String path;
    public String createDate;
    public String lastUpdate;
    public int mediaDisplayTypeId;
}
