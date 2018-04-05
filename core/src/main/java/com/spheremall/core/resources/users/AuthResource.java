package com.spheremall.core.resources.users;

import com.spheremall.core.entities.users.Token;
import com.spheremall.core.entities.users.User;
import com.spheremall.core.exceptions.EntityNotFoundException;
import com.spheremall.core.exceptions.ServiceException;
import com.spheremall.core.resources.Resource;

import java.io.IOException;

public interface AuthResource extends Resource<AuthResource, Token> {

    User login(String email, String password) throws EntityNotFoundException, IOException, ServiceException;

    Token auth(String email, String password) throws EntityNotFoundException, IOException, ServiceException;
}

