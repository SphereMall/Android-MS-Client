package com.spheremall.core.resources.users;

import com.spheremall.core.entities.users.Token;
import com.spheremall.core.entities.users.User;
import com.spheremall.core.exceptions.SphereMallException;
import com.spheremall.core.resources.Resource;

import java.io.IOException;

public interface AuthResource extends Resource<AuthResource, Token> {

    User login(String email, String password) throws SphereMallException, IOException;

    Token auth(String email, String password) throws SphereMallException, IOException;
}

