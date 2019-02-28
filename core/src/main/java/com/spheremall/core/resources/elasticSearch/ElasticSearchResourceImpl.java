package com.spheremall.core.resources.elasticSearch;

import com.spheremall.core.SMClient;
import com.spheremall.core.api.ESRequest;
import com.spheremall.core.api.Request;
import com.spheremall.core.api.configuration.Method;
import com.spheremall.core.api.response.ElasticSearchResponse;
import com.spheremall.core.api.response.ResponseMonada;
import com.spheremall.core.entities.Entity;
import com.spheremall.core.entities.Response;
import com.spheremall.core.exceptions.MethodNotFoundException;
import com.spheremall.core.exceptions.SphereMallException;
import com.spheremall.core.filters.Filter;
import com.spheremall.core.filters.Predicate;
import com.spheremall.core.filters.elasticsearch.ESSearchFilter;
import com.spheremall.core.filters.elasticsearch.compound.BoolFilter;
import com.spheremall.core.filters.elasticsearch.facets.ESCatalogFilter;
import com.spheremall.core.filters.elasticsearch.fulltext.MultiMatchFilter;
import com.spheremall.core.makers.ESResponseMaker;
import com.spheremall.core.makers.GridMaker;
import com.spheremall.core.makers.ObjectMaker;
import com.spheremall.core.mappers.ESEntityMapper;
import com.spheremall.core.mappers.FacetsMapper;
import com.spheremall.core.resources.BaseResource;
import com.spheremall.core.specifications.base.FilterSpecification;
import com.spheremall.core.utils.TextUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public Response<List<Entity>> facets(ESCatalogFilter filter, String groupBy, List<String> entities) throws IOException, SphereMallException, JSONException {
        Request smRequest = new Request(smClient, this);
        String uriAppend = smClient.getVersion() + "/" + getURI() + "/filter";

        HashMap<String, String> params = new HashMap<>();

        JSONObject config = filter.toConfig();
        if (config != null) {
            params.put("config", filter.toConfig().toString());
        }

        if (groupBy != null && !groupBy.isEmpty()) {
            params.put("groupBy", groupBy);
        }

        if (entities != null && !entities.isEmpty()) {
            String joinedEntities = TextUtils.join(",", entities);
            params.put("entities", joinedEntities);
        }

        JSONArray filterParams = filter.toParams();
        if (filterParams != null && filterParams.length() > 0) {
            params.put("params", filterParams.toString());
        }

        ResponseMonada responseMonada = smRequest.handle(Method.RAW_GET, uriAppend, params);

        if (responseMonada.hasError()) {
            throw new SphereMallException(responseMonada.getErrorResponse());
        }

        maker = new GridMaker(Entity.class);
        Response<List<Entity>> response = maker.makeAsList(responseMonada.getResponse());
        return new Response<>(new FacetsMapper().doObject(response), response.meta());
    }

    @Override
    public Response<List<Entity>> search(String query, List<String> indexes) throws SphereMallException, IOException {
        ESSearchFilter filter = new ESSearchFilter();
        String indexesArray[] = new String[indexes.size()];

        for (int i = 0; i < indexes.size(); i++) {
            indexesArray[i] = indexes.get(i);
        }

        filter.index(indexesArray);

        MultiMatchFilter matchFilter = new MultiMatchFilter("title_*", query);
        matchFilter.setFields(
                "html_*",
                "title_*",
                "short_description_*",
                "full_description_*"
        );

        BoolFilter boolSearchFilter = new BoolFilter();
        boolSearchFilter.must(matchFilter);

        filter.query(boolSearchFilter);

        filters(filter.asFilter());

        HashMap<String, String> params = getQueryParams();
        ResponseMonada responseMonada = request.handle(Method.GET, params.get("index") + "/_search", getQueryParams());

        ESResponseMaker esResponseMaker = new ESResponseMaker();
        ElasticSearchResponse searchResponse = esResponseMaker.makeSingle(responseMonada.getResponse()).data();

        ESEntityMapper esEntityMapper = new ESEntityMapper();
        List<Entity> entities = esEntityMapper.doObject(searchResponse);
        Map<String, String> meta = new HashMap<>();
        meta.put("count", String.valueOf(searchResponse.hits.total));
        return new Response<>(entities, meta);
    }

    @Override
    public Response<List<Entity>> search(String query) throws SphereMallException, IOException {
        List<String> indexes = new ArrayList<>();
        indexes.add("sm-products");
        indexes.add("sm-documents");
        return search(query, indexes);
    }

    @Override
    public Response<List<Entity>> search(String query, ESSearchFilter filter) throws SphereMallException, IOException {

        filters(filter.asFilter());

        HashMap<String, String> params = getQueryParams();
        ResponseMonada responseMonada = request.handle(Method.GET, params.get("index") + "/_search", getQueryParams());

        ESResponseMaker esResponseMaker = new ESResponseMaker();
        ElasticSearchResponse searchResponse = esResponseMaker.makeSingle(responseMonada.getResponse()).data();

        ESEntityMapper esEntityMapper = new ESEntityMapper();
        List<Entity> entities = esEntityMapper.doObject(searchResponse);
        Map<String, String> meta = new HashMap<>();
        meta.put("count", String.valueOf(searchResponse.hits.total));
        return new Response<>(entities, meta);
    }

    @Override
    public Response<List<Entity>> fetch() throws SphereMallException, IOException {
        HashMap<String, String> params = getQueryParams();
        ResponseMonada responseMonada = request.handle(Method.GET, params.get("index") + "/_search", getQueryParams());

        ESResponseMaker esResponseMaker = new ESResponseMaker();
        ElasticSearchResponse searchResponse = esResponseMaker.makeSingle(responseMonada.getResponse()).data();

        ESEntityMapper esEntityMapper = new ESEntityMapper();
        List<Entity> entities = esEntityMapper.doObject(searchResponse);
        Map<String, String> meta = new HashMap<>();
        meta.put("count", String.valueOf(searchResponse.hits.total));
        return new Response<>(entities, meta);
    }

    @Override
    public Response<List<Entity>> fetchTest() throws SphereMallException, IOException {
        HashMap<String, String> params = getQueryParams();
        ResponseMonada responseMonada = request.handle(Method.GET, params.get("index") + "/_search", getQueryParams());

        ESResponseMaker esResponseMaker = new ESResponseMaker();
        ElasticSearchResponse searchResponse = esResponseMaker.makeSingle(responseMonada.getResponse()).data();

        ESEntityMapper esEntityMapper = new ESEntityMapper();
        List<Entity> entities = esEntityMapper.doObject(searchResponse);
        Map<String, String> meta = new HashMap<>();
        meta.put("count", String.valueOf(searchResponse.hits.total));
        return new Response<>(entities, meta);
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

            params.putAll(getExtraParams());

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
        super.filters(filter);
        return this;
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
