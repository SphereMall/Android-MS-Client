package com.spheremall.core.resources.shop;

import com.spheremall.core.SMClient;
import com.spheremall.core.entities.shop.OrderItem;
import com.spheremall.core.makers.ObjectMaker;
import com.spheremall.core.resources.BaseResource;

public class OrderItemsResourceImpl extends BaseResource<OrderItem, OrderItemsResource> implements OrderItemsResource {

    public OrderItemsResourceImpl(SMClient smClient) {
        super(smClient);
    }

    public OrderItemsResourceImpl(SMClient client, String version) {
        super(client, version);
    }

    @Override
    public String getURI() {
        return "orderitems";
    }

    @Override
    public ObjectMaker<OrderItem> initializeMaker() {
        return new ObjectMaker<>(OrderItem.class);
    }

    @Override
    protected OrderItemsResource currentContext() {
        return this;
    }
}
