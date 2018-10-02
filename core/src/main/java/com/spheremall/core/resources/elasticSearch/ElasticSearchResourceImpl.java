package com.spheremall.core.resources.elasticSearch;

import com.spheremall.core.SMClient;
import com.spheremall.core.api.ESRequest;
import com.spheremall.core.api.configuration.Method;
import com.spheremall.core.api.response.ElasticSearchResponse;
import com.spheremall.core.api.response.ResponseMonada;
import com.spheremall.core.entities.Entity;
import com.spheremall.core.entities.Facets;
import com.spheremall.core.entities.Response;
import com.spheremall.core.exceptions.MethodNotFoundException;
import com.spheremall.core.exceptions.SphereMallException;
import com.spheremall.core.filters.Filter;
import com.spheremall.core.filters.Predicate;
import com.spheremall.core.makers.ESResponseMaker;
import com.spheremall.core.makers.ObjectMaker;
import com.spheremall.core.mappers.ESEntityMapper;
import com.spheremall.core.resources.BaseResource;
import com.spheremall.core.specifications.base.FilterSpecification;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class ElasticSearchResourceImpl extends BaseResource<Entity, ElasticSearchResource> implements ElasticSearchResource {

    public ElasticSearchResourceImpl(SMClient smClient) {
        super(smClient);
        this.request = new ESRequest(smClient, this);
    }

    public ElasticSearchResourceImpl(SMClient client, String version) {
        super(client, version);
        this.request = new ESRequest(smClient, this);
    }

    @Override
    public String getURI() {
        return "elasticsearch";
    }

    @Override
    public ObjectMaker<Entity> initializeMaker() {
        return new ObjectMaker<>(Entity.class);
    }

    @Override
    protected ElasticSearchResource currentContext() {
        return this;
    }

    @Override
    public Response<Facets> facets() throws SphereMallException, IOException {
        return null;
    }


    @Override
    public List<Entity> fetch() throws SphereMallException, IOException {
        HashMap<String, String> params = getQueryParams();
        ResponseMonada responseMonada = request.handle(Method.GET, params.get("index") + "/_search", getQueryParams());

        ESResponseMaker esResponseMaker = new ESResponseMaker();
        Response<ElasticSearchResponse> searchResponse = esResponseMaker.makeSingle(responseMonada.getResponse());

        ESEntityMapper esEntityMapper = new ESEntityMapper();
        return esEntityMapper.doObject(searchResponse.data());
    }

    @Override
    public String allTest() throws SphereMallException, IOException {
        HashMap<String, String> params = getQueryParams();
        ResponseMonada responseMonada = request.handle(Method.GET, params.get("index") + "/_search", getQueryParams());

        return responseMonada.getResponse();
    }

    protected HashMap<String, String> getQueryParams() {
        HashMap<String, String> params = super.getQueryParams();

        try {
            JSONObject paramsJson = new JSONObject(params.get("where"));
            paramsJson.put("size", params.get("limit"));
            paramsJson.put("from", params.get("offset"));

            if (params.containsKey("size")) {
                paramsJson.put("size", params.get("size"));
            }

            params.clear();

            params.put("index", paramsJson.getString("index"));
            paramsJson.remove("index");
            params.put("body", paramsJson.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return params;
    }

    @Override
    public ElasticSearchResource filters(Filter filter) {
        return super.filters(filter);
    }

    @Override
    public ElasticSearchResource filters(Predicate... filters) {
        throw new MethodNotFoundException("Method filters(Predicate... filters) can not be use with Elasticsearch");
    }

    @Override
    public ElasticSearchResource filters(FilterSpecification filter) {
        throw new MethodNotFoundException("Method filters(FilterSpecification filter) can not be use with Elasticsearch");
    }

    @Override
    public int count() {
        throw new MethodNotFoundException("Method count() can not be use with Elasticsearch");
    }

    @Override
    public Response<Entity> get(int id) {
        throw new MethodNotFoundException("Method get() can not be use with Elasticsearch");
    }

    @Override
    public Response<Entity> update(Integer id, HashMap<String, String> params) {
        throw new MethodNotFoundException("Method update() can not be use with Elasticsearch");
    }

    @Override
    public Response<Entity> create(HashMap<String, String> params) {
        throw new MethodNotFoundException("Method create() can not be use with Elasticsearch");
    }

    @Override
    public Boolean delete(Integer id) {
        throw new MethodNotFoundException("Method delete() can not be use with Elasticsearch");
    }
}
