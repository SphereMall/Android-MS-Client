package com.spheremall.core.entities.products;

import com.spheremall.core.entities.Entity;
import com.spheremall.core.jsonapi.annotations.Type;

@Type("options")
public class Option extends Entity {
    public String title;
    public int visible;
    public String description;
    public int orderNumber;
}
