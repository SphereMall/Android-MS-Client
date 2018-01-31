package com.spheremall.core.specifications.base;

import com.spheremall.core.entities.Entity;
import com.spheremall.core.filters.Filter;
import com.spheremall.core.filters.FilterOperators;
import com.spheremall.core.filters.Predicate;
import com.spheremall.core.utils.PropertyUtils;

import java.util.ArrayList;
import java.util.List;

public class IsVisible<E extends Entity> implements FilterSpecification, EntitySpecification<E> {

    @Override
    public Boolean isSatisfied(E object) {
        if (PropertyUtils.objectHasProperty(object, "visible")) {
            return Boolean.parseBoolean(PropertyUtils.getValueByField(object, "visible"));
        }
        throw new IllegalArgumentException("Property 'visible' does not exist in class " + object.getClass().getSimpleName());
    }

    @Override
    public Filter asFilter() {
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(new Predicate("visible", FilterOperators.EQUAL, String.valueOf(1)));
        return new Filter(predicates);
    }
}
