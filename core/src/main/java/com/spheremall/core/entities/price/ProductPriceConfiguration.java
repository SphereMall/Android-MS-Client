package com.spheremall.core.entities.price;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.spheremall.core.jsonapi.IntegerIdHandler;
import com.spheremall.core.jsonapi.RelType;
import com.spheremall.core.jsonapi.annotations.Id;
import com.spheremall.core.jsonapi.annotations.Relationship;
import com.spheremall.core.jsonapi.annotations.Type;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Type("productPriceConfigurations")
public class ProductPriceConfiguration {

    @Id(IntegerIdHandler.class)
    public Integer id;

    public int productId;
    public int priceConfigurationId;

    @Relationship(value = "priceConfigurations", resolve = true, relType = RelType.RELATED)
    public List<PriceConfiguration> priceConfigurations;
}
