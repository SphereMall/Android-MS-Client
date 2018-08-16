package com.spheremall.core.shop;

public class UpdateBasketPredicate {
    public final int itemId;
    public final int amount;

    public UpdateBasketPredicate(int itemId, int amount) {
        this.itemId = itemId;
        this.amount = amount;
    }

}
