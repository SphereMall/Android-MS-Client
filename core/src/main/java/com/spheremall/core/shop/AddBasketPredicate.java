package com.spheremall.core.shop;

import java.util.List;

public class AddBasketPredicate {

    public final int id;
    public final int amount;
    public final List<AttributesPredicate> attributes;

    public AddBasketPredicate(int id) {
        this.id = id;
        this.amount = 0;
        this.attributes = null;
    }

    public AddBasketPredicate(int id, int amount) {
        this.id = id;
        this.amount = amount;
        this.attributes = null;
    }

    public AddBasketPredicate(int id, int amount, List<AttributesPredicate> attributes) {
        this.id = id;
        this.amount = amount;
        this.attributes = attributes;
    }

    public AddBasketPredicate(int id, List<AttributesPredicate> attributes) {
        this.id = id;
        this.amount = 0;
        this.attributes = attributes;
    }

    public List<AttributesPredicate> getAttributes() {
        return attributes;
    }
}
