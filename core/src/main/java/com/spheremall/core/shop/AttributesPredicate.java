package com.spheremall.core.shop;

public class AttributesPredicate {

    public String userValue = "";
    public final int attributeId;
    public final int attributeValueId;

    public AttributesPredicate(int attributeId, int attributeValueId) {
        this.attributeId = attributeId;
        this.attributeValueId = attributeValueId;
    }

    public AttributesPredicate(int attributeId, int attributeValueId, String userValue) {
        this.userValue = userValue;
        this.attributeId = attributeId;
        this.attributeValueId = attributeValueId;
    }
}
