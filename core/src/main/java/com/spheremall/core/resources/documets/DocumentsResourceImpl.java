package com.spheremall.core.resources.documets;


import com.spheremall.core.SMClient;
import com.spheremall.core.entities.documents.Document;
import com.spheremall.core.makers.ObjectMaker;
import com.spheremall.core.resources.BaseResource;

public class DocumentsResourceImpl extends BaseResource<Document, DocumentsResource> implements DocumentsResource {

    public DocumentsResourceImpl(SMClient smClient) {
        super(smClient);
    }

    @Override
    public String getURI() {
        return "documents";
    }

    @Override
    public ObjectMaker<Document> initializeMaker() {
        return new ObjectMaker<>(Document.class);
    }

    @Override
    protected DocumentsResource currentContext() {
        return this;
    }
}
