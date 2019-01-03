package com.spheremall.core.entities.products;

import com.spheremall.core.entities.Entity;
import com.spheremall.core.jsonapi.annotations.Type;

@Type("unitOfMeasure")
public class UnitOfMeasure extends Entity {
    public String title;
    public String code;
    public String visible;
    public String group;
    public String coefficient;
    public int unitOfMeasureGroupId;
}
