package com.spheremall.core.shop;

import java.util.Arrays;
import java.util.List;

public class BasketPredicate {

    public final int id;
    public final int amount;
    public final String compound;
    private List<AttributesPredicate> attributes = null;

    public BasketPredicate(int id) {
        this.id = id;
        this.amount = 0;
        this.compound = null;
    }

    public BasketPredicate(int id, int amount) {
        this.id = id;
        this.amount = amount;
        this.compound = null;
    }

    public BasketPredicate(int id, int amount, String compound) {
        this.id = id;
        this.amount = amount;
        this.compound = compound;
    }

    public BasketPredicate(int id, String compound) {
        this.id = id;
        this.amount = 0;
        this.compound = compound;
    }

    public List<AttributesPredicate> getAttributes() {
        return attributes;
    }

    public void setAttributes(AttributesPredicate... attributes) {
        this.attributes = Arrays.asList(attributes);
    }

    public void setAttributes(List<AttributesPredicate> attributes) {
        this.attributes = attributes;
    }
}
