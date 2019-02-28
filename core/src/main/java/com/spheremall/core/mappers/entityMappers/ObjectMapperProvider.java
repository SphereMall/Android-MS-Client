package com.spheremall.core.mappers.entityMappers;

public class ObjectMapperProvider {

    public ObjectMapper provide(String type) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        String mapperClassName = type.substring(0, 1).toUpperCase() + type.substring(1) + "Mapper";
        Object o = Class.forName("com.spheremall.core.mappers.entityMappers." + mapperClassName).newInstance();
        return (ObjectMapper) o;
    }
}
