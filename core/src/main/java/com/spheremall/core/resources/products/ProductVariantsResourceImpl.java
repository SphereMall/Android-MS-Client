package com.spheremall.core.resources.products;

import com.spheremall.core.SMClient;
import com.spheremall.core.entities.products.ProductVariant;
import com.spheremall.core.makers.ObjectMaker;
import com.spheremall.core.resources.BaseResource;

public class ProductVariantsResourceImpl extends BaseResource<ProductVariant, ProductVariantsResource> implements ProductVariantsResource {

    public ProductVariantsResourceImpl(SMClient smClient) {
        super(smClient);
    }

    public ProductVariantsResourceImpl(SMClient client, String version) {
        super(client, version);
    }

    @Override
    public String getURI() {
        return "productvariants";
    }

    @Override
    public ObjectMaker<ProductVariant> initializeMaker() {
        return new ObjectMaker<>(ProductVariant.class);
    }

    @Override
    protected ProductVariantsResource currentContext() {
        return this;
    }
}
