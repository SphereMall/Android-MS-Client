package com.spheremall.core.api.providers;

import com.spheremall.core.api.services.AuthService;
import com.spheremall.core.api.services.SMService;

public interface ApiServiceProvider {

    SMService sphereMallService();

    AuthService authService();
}
