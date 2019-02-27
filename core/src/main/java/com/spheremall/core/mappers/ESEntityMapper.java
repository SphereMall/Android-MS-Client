package com.spheremall.core.mappers;

import com.spheremall.core.api.response.ElasticSearchResponse;
import com.spheremall.core.entities.Entity;
import com.spheremall.core.makers.CatalogMaker;

import java.util.Iterator;
import java.util.List;

public class ESEntityMapper implements Mapper<ElasticSearchResponse, Entity> {

    @Override
    public List<Entity> doObject(ElasticSearchResponse obj) {
        CatalogMaker maker = new CatalogMaker(Entity.class);

        Iterator iterator = obj.hits.hits.iterator();
        System.out.println("start");
        while (iterator.hasNext()) {
            ElasticSearchResponse.Hits hits = (ElasticSearchResponse.Hits) iterator.next();
            maker.add(hits.source.scope);
            iterator.remove();
        }
        List<Entity> entities = maker.makeAsList("").data();
        System.out.println("end");
        return entities;
    }
}