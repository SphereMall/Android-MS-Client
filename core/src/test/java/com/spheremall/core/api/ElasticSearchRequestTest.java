package com.spheremall.core.api;

import com.spheremall.core.api.configuration.Method;
import com.spheremall.core.api.response.ResponseMonada;
import com.spheremall.core.exceptions.SphereMallException;
import com.spheremall.core.filters.elasticsearch.criterions.TermsFilterCriteria;
import com.spheremall.core.filters.elasticsearch.terms.TermsFilter;
import com.spheremall.core.resources.BaseResource;
import com.spheremall.core.resources.SetUpResourceTest;

import junit.framework.Assert;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;

public class ElasticSearchRequestTest extends SetUpResourceTest {

    @Test
    public void testRequest() throws IOException, SphereMallException, JSONException {
        ESRequest ESRequest = new ESRequest(client, (BaseResource) client.elasticSearch());

        TermsFilter termsFilter = new TermsFilter(new TermsFilterCriteria("price", "20000"));

        JSONObject field = new JSONObject();
        field.put("title_fr", "Store enrouleur");

        JSONObject matchObj = new JSONObject();
        matchObj.put("match", field);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("query", matchObj);
        jsonObject.put("size", 1);

        HashMap<String, String> map = new HashMap<>();
        map.put("body", jsonObject.toString());

        ResponseMonada responseMonada = ESRequest.handle(Method.GET, "sm-products/_search", map);

        Assert.assertNotNull(responseMonada.getResponse());
    }
}