package com.spheremall.core.specifications.users;

import com.spheremall.core.entities.users.User;
import com.spheremall.core.filters.Filter;
import com.spheremall.core.filters.FilterOperators;
import com.spheremall.core.filters.Predicate;
import com.spheremall.core.specifications.base.FilterSpecification;

import java.util.ArrayList;
import java.util.List;

public class IsUserSubscriber implements UserSpecification, FilterSpecification {

    @Override
    public Boolean isSatisfied(User user) {
        return user.getId() != 0 && user.isSubscriber == 1;
    }

    @Override
    public Filter asFilter() {
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(new Predicate("isSubscriber", FilterOperators.EQUAL, String.valueOf(1)));
        return new Filter(predicates);
    }
}
