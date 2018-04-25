package com.spheremall.core.specifications;

import com.spheremall.core.entities.users.User;
import com.spheremall.core.exceptions.SphereMallException;
import com.spheremall.core.filters.FilterOperators;
import com.spheremall.core.filters.Predicate;
import com.spheremall.core.resources.SetUpResourceTest;
import com.spheremall.core.specifications.users.IsUserRegistered;

import org.junit.Assert;
import org.junit.Test;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.util.HashMap;

public class IsUserRegisteredTest extends SetUpResourceTest {

    private String email = "ms-android-sdk-email-test@test.com";
    private String password = "to-md5";

    @Test
    public void testIsUserRegisteredSpecificationSingle() throws SphereMallException, IOException {
        String hash = BCrypt.hashpw(password, BCrypt.gensalt());
        User user;
        try {
            user = client.users().filters(
                    new Predicate("email", FilterOperators.EQUAL, email))
                    .first().data();
            client.users().delete(user.getId());
            user = createNewUser(hash);
        } catch (Throwable e) {
            user = createNewUser(hash);
        }

        Assert.assertNotNull(user);
        Assert.assertEquals(email, user.email);
        Assert.assertEquals(hash, user.password);

        IsUserRegistered specification = new IsUserRegistered(user.email, password);

        Assert.assertTrue(specification.isSatisfied(user));
        Assert.assertTrue(client.users().delete(user.getId()));
    }

    private User createNewUser(String hash) throws SphereMallException, IOException {
        User user;
        HashMap<String, String> params = new HashMap<>();
        params.put("email", email);
        params.put("password", hash);
        user = client.users().create(params).data();
        return user;
    }
}
