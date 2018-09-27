package com.spheremall.core.resources.documets;

import com.spheremall.core.SMClient;
import com.spheremall.core.entities.documents.Comment;
import com.spheremall.core.makers.ObjectMaker;
import com.spheremall.core.resources.BaseResource;

public class CommentsResourceImpl extends BaseResource<Comment, CommentsResource> implements CommentsResource {

    public CommentsResourceImpl(SMClient smClient) {
        super(smClient);
    }

    public CommentsResourceImpl(SMClient client, String version) {
        super(client, version);
    }

    @Override
    public String getURI() {
        return "comments";
    }

    @Override
    public ObjectMaker<Comment> initializeMaker() {
        return new ObjectMaker<>(Comment.class);
    }

    @Override
    protected CommentsResource currentContext() {
        return this;
    }
}
