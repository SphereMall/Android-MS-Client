package com.spheremall.core.specifications.base;

public interface Specification<T> {

    Boolean isSatisfied(T object);
}
