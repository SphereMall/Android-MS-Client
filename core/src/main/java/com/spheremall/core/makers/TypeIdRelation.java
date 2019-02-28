package com.spheremall.core.makers;

import java.util.Objects;

public class TypeIdRelation {
    public final int id;
    public final String type;

    public TypeIdRelation(int id, String type) {
        this.id = id;
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TypeIdRelation)) return false;
        TypeIdRelation that = (TypeIdRelation) o;
        return id == that.id &&
                Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type);
    }
}
