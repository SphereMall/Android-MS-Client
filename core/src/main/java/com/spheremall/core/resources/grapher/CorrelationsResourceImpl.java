package com.spheremall.core.resources.grapher;

import com.spheremall.core.SMClient;
import com.spheremall.core.api.configuration.Method;
import com.spheremall.core.api.response.ResponseMonada;
import com.spheremall.core.entities.Entity;
import com.spheremall.core.entities.Response;
import com.spheremall.core.exceptions.EntityNotFoundException;
import com.spheremall.core.exceptions.MethodNotFoundException;
import com.spheremall.core.exceptions.SphereMallException;
import com.spheremall.core.makers.GridMaker;
import com.spheremall.core.makers.ObjectMaker;
import com.spheremall.core.utils.CorrelationTypeHelper;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class CorrelationsResourceImpl extends GrapherResource<CorrelationsResource> implements CorrelationsResource {

    public CorrelationsResourceImpl(SMClient smClient) {
        super(smClient);
    }

    public CorrelationsResourceImpl(SMClient client, String version) {
        super(client, version);
    }

    @Override
    public Response<List<Entity>> getById(int id, Class forClassName) throws SphereMallException, IOException {
        HashMap<String, String> params = getQueryParams();
        String type = CorrelationTypeHelper.getGraphTypeByClass(forClassName);
        String uriAppend = type + "/" + id;
        ResponseMonada responseMonada = request.handle(Method.GET, uriAppend, params);
        if (responseMonada.hasError()) {
            throw new EntityNotFoundException(responseMonada.getErrorResponse());
        }
        maker = new GridMaker(Entity.class);
        return maker.makeAsList(responseMonada.getResponse());
    }

    @Override
    public String getURI() {
        return "correlations";
    }

    @Override
    public ObjectMaker<Entity> initializeMaker() {
        return new ObjectMaker<>(Entity.class);
    }

    @Override
    protected CorrelationsResource currentContext() {
        return this;
    }

    @Override
    public Response<Entity> get(int id) {
        throw new MethodNotFoundException("Method data() can not be use with correlations");
    }

    @Override
    public Response<Entity> update(Integer id, HashMap<String, String> params) {
        throw new MethodNotFoundException("Method update() can not be use with correlations");
    }

    @Override
    public Response<Entity> create(HashMap<String, String> params) {
        throw new MethodNotFoundException("Method create() can not be use with correlations");
    }

    @Override
    public Boolean delete(Integer id) {
        throw new MethodNotFoundException("Method delete() can not be use with correlations");
    }
}
