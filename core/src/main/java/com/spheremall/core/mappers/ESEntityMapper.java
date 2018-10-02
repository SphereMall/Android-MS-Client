package com.spheremall.core.mappers;

import com.spheremall.core.api.response.ElasticSearchResponse;
import com.spheremall.core.entities.Entity;
import com.spheremall.core.makers.GridMaker;

import java.util.ArrayList;
import java.util.List;

public class ESEntityMapper implements Mapper<ElasticSearchResponse, Entity> {

    @Override
    public List<Entity> doObject(ElasticSearchResponse obj) {
        GridMaker objectMaker = new GridMaker(Entity.class);

        List<Entity> products = new ArrayList<>();
        for (ElasticSearchResponse.Hits item : obj.hits.hits) {
            List<Entity> entities = objectMaker.makeAsList(item.source.scope).data();
            products.addAll(entities);
        }
        return products;
    }
}