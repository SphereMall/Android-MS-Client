package com.spheremall.core.resources.products;

import com.spheremall.core.SMClient;
import com.spheremall.core.entities.products.Media;
import com.spheremall.core.makers.ObjectMaker;
import com.spheremall.core.resources.BaseResource;

public class MediaResourceImpl extends BaseResource<Media, MediaResource> implements MediaResource {

    public MediaResourceImpl(SMClient smClient) {
        super(smClient);
    }

    @Override
    public String getURI() {
        return "media";
    }

    @Override
    public ObjectMaker<Media> initializeMaker() {
        return new ObjectMaker<>(Media.class);
    }

    @Override
    protected MediaResource currentContext() {
        return this;
    }
}
