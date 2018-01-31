package com.spheremall.core.entities.documents;

import com.spheremall.core.entities.Entity;
import com.spheremall.core.entities.products.FunctionalName;
import com.spheremall.core.jsonapi.RelType;
import com.spheremall.core.jsonapi.annotations.Relationship;
import com.spheremall.core.jsonapi.annotations.Type;

import java.util.List;

@Type("documents")
public class Document extends Entity {
    public int functionalNameId;
    public String title;
    public String urlCode;
    public String createDate;
    public String lastUpdate;
    public String visible;

    @Relationship(value = "functionalNames", resolve = true, relType = RelType.RELATED)
    private List<FunctionalName> functionalNames;
}
