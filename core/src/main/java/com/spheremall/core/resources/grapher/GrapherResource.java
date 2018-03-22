package com.spheremall.core.resources.grapher;

import com.spheremall.core.SMClient;
import com.spheremall.core.entities.Entity;
import com.spheremall.core.resources.BaseResource;
import com.spheremall.core.resources.Resource;

import java.util.HashMap;

public abstract class GrapherResource<R extends Resource> extends BaseResource<Entity, R> implements Resource<R, Entity> {

    public GrapherResource(SMClient smClient) {
        super(smClient);
    }

    public GrapherResource(SMClient client, String version) {
        super(client, version);
    }

    @Override
    protected HashMap<String, String> getQueryParams() {
        HashMap<String, String> params = super.getQueryParams();
        if (params.get("where") == null || params.get("where").equals("")) {
            return params;
        }
        String whereCondition = params.get("where");
        params.remove("where");

        String[] whereArray = whereCondition.split("&");
        for (String item : whereArray) {
            String[] keyValue = item.split("=");
            params.put(keyValue[0], keyValue[1]);
        }

        return params;
    }
}
