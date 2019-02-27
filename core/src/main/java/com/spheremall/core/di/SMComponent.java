package com.spheremall.core.di;

import com.spheremall.core.SMClient;

import dagger.Component;

@Component(modules = ApiModule.class)
public interface SMComponent {
    void inject(SMClient client);
}
