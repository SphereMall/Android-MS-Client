package com.spheremall.core.jsonapi.models;


import com.spheremall.core.entities.Entity;
import com.spheremall.core.jsonapi.annotations.Type;

import java.util.Objects;

@Type("documents")
public class Document extends Entity {
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Document)) return false;
        Document document = (Document) o;
        return Objects.equals(title, document.title);
    }

    @Override
    public int hashCode() {

        return Objects.hash(title);
    }
}
