package com.spheremall.core.entities.products;

import com.spheremall.core.entities.Entity;
import com.spheremall.core.jsonapi.annotations.Type;

@Type("attributeGroups")
public class AttributeGroup extends Entity {

    public String title;
    public int visible;
    public int orderNumber;
    public String lastUpdate;
}
