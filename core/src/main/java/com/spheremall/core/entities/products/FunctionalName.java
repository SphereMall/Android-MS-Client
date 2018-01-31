package com.spheremall.core.entities.products;

import com.spheremall.core.entities.Entity;
import com.spheremall.core.jsonapi.annotations.Type;

@Type("functionalNames")
public class FunctionalName extends Entity {

    public String code;
    public String title;
}