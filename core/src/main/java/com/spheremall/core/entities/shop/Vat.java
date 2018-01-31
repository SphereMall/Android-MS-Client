package com.spheremall.core.entities.shop;

import com.spheremall.core.entities.Entity;
import com.spheremall.core.jsonapi.annotations.Type;

@Type("vat")
public class Vat extends Entity {
    public float percent;
    public int exclude;
}
