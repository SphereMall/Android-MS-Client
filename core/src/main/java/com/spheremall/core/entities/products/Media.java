package com.spheremall.core.entities.products;

import com.spheremall.core.jsonapi.annotations.Type;
import com.spheremall.core.entities.Entity;

@Type("images")
public class Media extends Entity {

    public int objectId;
    public String title;
    public String imageName;
    public int orderNumber;
    public int imageTypeId;
    public int visible;
    public String path;
}
