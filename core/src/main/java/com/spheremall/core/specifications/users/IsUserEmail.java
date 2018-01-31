package com.spheremall.core.specifications.users;

import com.spheremall.core.entities.users.User;
import com.spheremall.core.filters.Filter;
import com.spheremall.core.filters.FilterOperators;
import com.spheremall.core.filters.Predicate;
import com.spheremall.core.specifications.base.FilterSpecification;

import java.util.ArrayList;
import java.util.List;

public class IsUserEmail implements UserSpecification, FilterSpecification {

    private final String email;

    public IsUserEmail(String email) {
        this.email = email;
    }

    @Override
    public Filter asFilter() {
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(new Predicate("email", FilterOperators.EQUAL, email));
        return new Filter(predicates);
    }

    @Override
    public Boolean isSatisfied(User user) {
        return user.email.equals(this.email);
    }
}
