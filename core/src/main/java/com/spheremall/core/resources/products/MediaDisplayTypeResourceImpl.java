package com.spheremall.core.resources.products;

import com.spheremall.core.SMClient;
import com.spheremall.core.entities.products.MediaDisplayType;
import com.spheremall.core.makers.ObjectMaker;
import com.spheremall.core.resources.BaseResource;

public class MediaDisplayTypeResourceImpl extends BaseResource<MediaDisplayType, MediaDisplayTypeResource> implements MediaDisplayTypeResource {

    public MediaDisplayTypeResourceImpl(SMClient smClient) {
        super(smClient);
    }

    public MediaDisplayTypeResourceImpl(SMClient client, String version) {
        super(client, version);
    }

    @Override
    public String getURI() {
        return "mediadisplaytypes";
    }

    @Override
    public ObjectMaker<MediaDisplayType> initializeMaker() {
        return new ObjectMaker<>(MediaDisplayType.class);
    }

    @Override
    protected MediaDisplayTypeResource currentContext() {
        return this;
    }
}
