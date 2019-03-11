package com.spheremall.core.mappers;

import com.spheremall.core.api.response.ElasticSearchResponse;
import com.spheremall.core.entities.Entity;
import com.spheremall.core.makers.CatalogMaker;

import java.util.List;

public class ESEntityMapper implements Mapper<ElasticSearchResponse, Entity> {

    @Override
    public List<Entity> doObject(ElasticSearchResponse obj) {
        CatalogMaker maker = new CatalogMaker(Entity.class);

        for (ElasticSearchResponse.Hits hits : obj.hits.hits) {
            maker.add(hits.source.scope);
        }

        return maker.makeAsList("").data();
    }
}