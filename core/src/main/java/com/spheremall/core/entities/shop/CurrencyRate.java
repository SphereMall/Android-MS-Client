package com.spheremall.core.entities.shop;

import com.spheremall.core.entities.Entity;
import com.spheremall.core.jsonapi.annotations.Type;

@Type("currenciesRate")
public class CurrencyRate extends Entity {

    public int fromId;
    public int toId;
    public float rate;
    public String lastUpdate;
}
