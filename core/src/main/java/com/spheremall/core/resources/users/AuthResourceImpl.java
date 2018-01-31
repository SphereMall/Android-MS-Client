package com.spheremall.core.resources.users;

import com.spheremall.core.SMClient;
import com.spheremall.core.api.AuthRequest;
import com.spheremall.core.api.configuration.ApiConstants;
import com.spheremall.core.api.configuration.Method;
import com.spheremall.core.api.response.ResponseMonada;
import com.spheremall.core.entities.users.Token;
import com.spheremall.core.exceptions.EntityNotFoundException;
import com.spheremall.core.exceptions.ServiceException;
import com.spheremall.core.makers.AuthMaker;
import com.spheremall.core.makers.ObjectMaker;
import com.spheremall.core.resources.BaseResource;

import java.io.IOException;
import java.util.HashMap;

public class AuthResourceImpl extends BaseResource<Token, AuthResource> implements AuthResource {

    public AuthResourceImpl(SMClient smClient) {
        super(smClient);
    }

    @Override
    public String login(String email, String password) throws EntityNotFoundException, IOException, ServiceException {
        String uriAppend = "token";

        HashMap<String, String> params = new HashMap<>();
        params.put(ApiConstants.API_CLIENT_ID_TITLE, smClient.getClientId());
        params.put(ApiConstants.API_SECRET_TITLE, smClient.getSecretKey());
        AuthRequest authRequest = new AuthRequest(this.smClient, this);
        ResponseMonada responseMonada = authRequest.handle(Method.POST, uriAppend, params);
        if (responseMonada.hasError()) {
            throw new EntityNotFoundException(responseMonada.getErrorResponse().error.message);
        }
        Token token = maker.makeSingle(responseMonada.getResponse()).data();
        if (token == null || token.data.size() == 0 || token.data.get(0).token.isEmpty()) {
            throw new EntityNotFoundException();
        }
        smClient.getPreferencesManager().setToken(token.data.get(0).token);
        return smClient.getPreferencesManager().getToken();
    }

    @Override
    public String getURI() {
        return "admin";
    }

    @Override
    public ObjectMaker<Token> initializeMaker() {
        return new AuthMaker(Token.class);
    }

    @Override
    protected AuthResource currentContext() {
        return this;
    }
}
