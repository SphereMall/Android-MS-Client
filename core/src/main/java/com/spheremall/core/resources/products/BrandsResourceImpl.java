package com.spheremall.core.resources.products;

import com.spheremall.core.SMClient;
import com.spheremall.core.entities.products.Brand;
import com.spheremall.core.makers.ObjectMaker;
import com.spheremall.core.resources.BaseResource;

public class BrandsResourceImpl extends BaseResource<Brand, BrandsResource> implements BrandsResource {

    public BrandsResourceImpl(SMClient smClient) {
        super(smClient);
    }

    @Override
    public String getURI() {
        return "brands";
    }

    @Override
    public ObjectMaker<Brand> initializeMaker() {
        return new ObjectMaker<>(Brand.class);
    }

    @Override
    protected BrandsResource currentContext() {
        return this;
    }
}
