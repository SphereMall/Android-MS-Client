package com.spheremall.core.api.configuration;

public interface ApiConfigurationFactory<T> {
    T createConfiguration();
}
