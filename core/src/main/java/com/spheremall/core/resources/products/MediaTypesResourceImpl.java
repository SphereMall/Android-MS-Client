package com.spheremall.core.resources.products;

import com.spheremall.core.SMClient;
import com.spheremall.core.entities.products.MediaType;
import com.spheremall.core.makers.ObjectMaker;
import com.spheremall.core.resources.BaseResource;

public class MediaTypesResourceImpl extends BaseResource<MediaType, MediaTypesResource> implements MediaTypesResource {

    public MediaTypesResourceImpl(SMClient smClient) {
        super(smClient);
    }

    @Override
    public String getURI() {
        return "imagetypes";
    }

    @Override
    public ObjectMaker<MediaType> initializeMaker() {
        return new ObjectMaker<>(MediaType.class);
    }

    @Override
    protected MediaTypesResource currentContext() {
        return this;
    }
}
