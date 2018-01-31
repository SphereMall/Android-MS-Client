package com.spheremall.core.resources.products;

import com.spheremall.core.SMClient;
import com.spheremall.core.entities.products.CatalogItem;
import com.spheremall.core.makers.ObjectMaker;
import com.spheremall.core.resources.BaseResource;

public class CatalogItemsResourceImpl extends BaseResource<CatalogItem, CatalogItemsResource> implements CatalogItemsResource {

    public CatalogItemsResourceImpl(SMClient smClient) {
        super(smClient);
    }

    @Override
    public String getURI() {
        return "catalogitems";
    }

    @Override
    public ObjectMaker<CatalogItem> initializeMaker() {
        return new ObjectMaker<>(CatalogItem.class);
    }

    @Override
    protected CatalogItemsResource currentContext() {
        return this;
    }
}
