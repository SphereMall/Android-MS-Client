package com.spheremall.core.resources.products;

import com.spheremall.core.SMClient;
import com.spheremall.core.entities.products.Product;
import com.spheremall.core.makers.ObjectMaker;
import com.spheremall.core.resources.full.FullResourceImpl;

public class ProductResourceImpl extends FullResourceImpl<Product, ProductResource> implements ProductResource {

    public ProductResourceImpl(SMClient client) {
        super(client);
    }

    @Override
    public String getURI() {
        return "products";
    }

    @Override
    public ObjectMaker<Product> initializeMaker() {
        return new ObjectMaker<>(Product.class);
    }

    @Override
    protected ProductResource currentContext() {
        return this;
    }


}
