package com.spheremall.core.specifications.users;

import com.spheremall.core.entities.users.User;
import com.spheremall.core.filters.Filter;
import com.spheremall.core.filters.FilterOperators;
import com.spheremall.core.filters.Predicate;
import com.spheremall.core.specifications.base.FilterSpecification;
import com.spheremall.core.specifications.base.Specification;

import org.mindrot.jbcrypt.BCrypt;

import java.util.ArrayList;

public class IsUserRegistered implements Specification<User>, FilterSpecification {

    public final String email;
    public final String password;

    public IsUserRegistered(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Override
    public Boolean isSatisfied(User user) {
        return email.equals(user.email) && BCrypt.checkpw(password, user.password);
    }

    @Override
    public Filter asFilter() {
        return new Filter(new ArrayList<Predicate>() {{
            add(new Predicate("email", FilterOperators.EQUAL, email));
        }});
    }
}
