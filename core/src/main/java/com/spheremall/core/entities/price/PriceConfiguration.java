package com.spheremall.core.entities.price;

import com.spheremall.core.entities.Entity;
import com.spheremall.core.jsonapi.annotations.Type;

import java.util.List;

@Type("priceConfigurations")
public class PriceConfiguration extends Entity {

    public List<String> affectAttributes;
    public String code;
}
