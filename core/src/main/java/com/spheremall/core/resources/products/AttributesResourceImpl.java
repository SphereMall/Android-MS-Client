package com.spheremall.core.resources.products;

import com.spheremall.core.SMClient;
import com.spheremall.core.api.configuration.Method;
import com.spheremall.core.api.response.ResponseMonada;
import com.spheremall.core.entities.Response;
import com.spheremall.core.entities.products.Attribute;
import com.spheremall.core.exceptions.EntityNotFoundException;
import com.spheremall.core.exceptions.SphereMallException;
import com.spheremall.core.makers.ObjectMaker;
import com.spheremall.core.resources.BaseResource;

import java.io.IOException;
import java.util.List;

public class AttributesResourceImpl extends BaseResource<Attribute, AttributesResource> implements AttributesResource {

    public AttributesResourceImpl(SMClient smClient) {
        super(smClient);
    }

    @Override
    public String getURI() {
        return "attributes";
    }

    @Override
    public ObjectMaker<Attribute> initializeMaker() {
        return new ObjectMaker<>(Attribute.class);
    }

    @Override
    protected AttributesResource currentContext() {
        return this;
    }

    @Override
    public Response<List<Attribute>> belong(Class entityClass) throws SphereMallException, IOException {
        String className = entityClass.getSimpleName().toLowerCase();
        String uriAppend = "belong/" + className + "s";
        ResponseMonada responseMonada = request.handle(Method.GET, uriAppend, super.getQueryParams());
        if (responseMonada.hasError()) {
            throw new EntityNotFoundException(responseMonada.getErrorResponse());
        }
        return maker.makeAsList(responseMonada.getResponse());
    }

    @Override
    public Response<List<Attribute>> belong(Class entityClass, int groupId) throws SphereMallException, IOException {
        String className = entityClass.getSimpleName().toLowerCase();
        String uriAppend = "belong/" + className + "s" + "/" + groupId;
        ResponseMonada responseMonada = request.handle(Method.GET, uriAppend, super.getQueryParams());
        if (responseMonada.hasError()) {
            throw new EntityNotFoundException(responseMonada.getErrorResponse());
        }
        return maker.makeAsList(responseMonada.getResponse());
    }

    @Override
    public Response<Attribute> belong(Class entityClass, int groupId, int attributeId) throws SphereMallException, IOException {
        String className = entityClass.getSimpleName().toLowerCase();
        String uriAppend = "belong/" + className + "s" + "/" + groupId + "/" + attributeId;
        ResponseMonada responseMonada = request.handle(Method.GET, uriAppend, super.getQueryParams());
        if (responseMonada.hasError()) {
            throw new EntityNotFoundException(responseMonada.getErrorResponse());
        }
        return maker.makeSingle(responseMonada.getResponse());
    }
}
