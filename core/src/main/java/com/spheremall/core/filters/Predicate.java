package com.spheremall.core.filters;

public class Predicate {
    public final String field;
    public final String operator;
    public final String value;

    public Predicate(String field, String operator, String value) {
        this.field = field;
        this.operator = operator;
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Predicate predicate = (Predicate) o;

        if (field != null ? !field.equals(predicate.field) : predicate.field != null) return false;

        if (operator != null ? !operator.equals(predicate.operator) : predicate.operator != null)
            return false;
        return value != null ? value.equals(predicate.value) : predicate.value == null;
    }

    @Override
    public int hashCode() {
        int result = field != null ? field.hashCode() : 0;
        result = 31 * result + (operator != null ? operator.hashCode() : 0);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }
}
