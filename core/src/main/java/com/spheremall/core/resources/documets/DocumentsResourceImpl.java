package com.spheremall.core.resources.documets;


import com.spheremall.core.SMClient;
import com.spheremall.core.api.configuration.Method;
import com.spheremall.core.api.response.ResponseMonada;
import com.spheremall.core.entities.Response;
import com.spheremall.core.entities.documents.Document;
import com.spheremall.core.entities.products.Attribute;
import com.spheremall.core.entities.products.AttributeValue;
import com.spheremall.core.exceptions.EntityNotFoundException;
import com.spheremall.core.exceptions.SphereMallException;
import com.spheremall.core.makers.ObjectMaker;
import com.spheremall.core.resources.BaseResource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

    @Override
    public Response<List<Document>> detail() throws SphereMallException, IOException {
        String urlAppend = "detail/list";
        HashMap<String, String> params = getQueryParams();
        ResponseMonada responseMonada = request.handle(Method.GET, urlAppend, params);
        if (responseMonada.hasError()) {
            throw new EntityNotFoundException(responseMonada.getErrorResponse());
        }

        Response<List<Document>> documentsResponse = maker.makeAsList(responseMonada.getResponse());
        List<Document> documents = documentsResponse.data();
        List<Document> combinedDocuments = new ArrayList<>();
        for (Document document : documents) {
            combinedDocuments.add(combineAttributeValues(document));
        }

        return new Response<>(combinedDocuments, documentsResponse.meta());
    }

    @Override
    public Document detail(int id) throws SphereMallException, IOException {
        String urlAppend = "detail/" + id;
        return getDocument(urlAppend);
    }

    @Override
    public Document detail(String urlCode) throws SphereMallException, IOException {
        String urlAppend = "detail/url/" + urlCode;
        return getDocument(urlAppend);
    }

    private Document getDocument(String urlAppend) throws SphereMallException, IOException {
        ResponseMonada responseMonada = request.handle(Method.GET, urlAppend, new HashMap<>());
        if (responseMonada.hasError()) {
            throw new EntityNotFoundException(responseMonada.getErrorResponse());
        }

        Document document = maker.makeSingle(responseMonada.getResponse()).data();
        return combineAttributeValues(document);
    }

    private Document combineAttributeValues(Document document) {
        for (Attribute attr : document.attributes) {
            attr.attributeValues = new ArrayList<>();
            for (AttributeValue attrValue : document.attributeValues) {
                if (attr.getId() == attrValue.attributeId) {
                    attr.attributeValues.add(attrValue);
                }
            }
        }
        return document;
    }
}
