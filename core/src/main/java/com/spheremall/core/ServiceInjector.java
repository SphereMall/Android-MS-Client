package com.spheremall.core;

import com.spheremall.core.exceptions.SphereMallException;
import com.spheremall.core.resources.documets.DocumentsResource;
import com.spheremall.core.resources.documets.DocumentsResourceImpl;
import com.spheremall.core.resources.elasticSearch.ElasticSearchResource;
import com.spheremall.core.resources.elasticSearch.ElasticSearchResourceImpl;
import com.spheremall.core.resources.grapher.CorrelationsResource;
import com.spheremall.core.resources.grapher.CorrelationsResourceImpl;
import com.spheremall.core.resources.grapher.GridResource;
import com.spheremall.core.resources.grapher.GridResourceImpl;
import com.spheremall.core.resources.price.ProductPriceConfigurationsResource;
import com.spheremall.core.resources.price.ProductPriceConfigurationsResourceImpl;
import com.spheremall.core.resources.products.AttributeDisplayTypesResource;
import com.spheremall.core.resources.products.AttributeDisplayTypesResourceImpl;
import com.spheremall.core.resources.products.AttributeGroupsEntitiesResource;
import com.spheremall.core.resources.products.AttributeGroupsEntitiesResourceImpl;
import com.spheremall.core.resources.products.AttributeGroupsResource;
import com.spheremall.core.resources.products.AttributeGroupsResourceImpl;
import com.spheremall.core.resources.products.AttributeTypesResource;
import com.spheremall.core.resources.products.AttributeTypesResourceImpl;
import com.spheremall.core.resources.products.AttributeValuesResource;
import com.spheremall.core.resources.products.AttributeValuesResourceImpl;
import com.spheremall.core.resources.products.AttributesResource;
import com.spheremall.core.resources.products.AttributesResourceImpl;
import com.spheremall.core.resources.products.BrandsResource;
import com.spheremall.core.resources.products.BrandsResourceImpl;
import com.spheremall.core.resources.products.CatalogItemsResource;
import com.spheremall.core.resources.products.CatalogItemsResourceImpl;
import com.spheremall.core.resources.products.EntitiesResource;
import com.spheremall.core.resources.products.EntitiesResourceImpl;
import com.spheremall.core.resources.products.EntityAttributesResource;
import com.spheremall.core.resources.products.EntityAttributesResourceImpl;
import com.spheremall.core.resources.products.FunctionalNamesResource;
import com.spheremall.core.resources.products.FunctionalNamesResourceImpl;
import com.spheremall.core.resources.products.MediaResource;
import com.spheremall.core.resources.products.MediaResourceImpl;
import com.spheremall.core.resources.products.MediaTypesResource;
import com.spheremall.core.resources.products.MediaTypesResourceImpl;
import com.spheremall.core.resources.products.OptionsResource;
import com.spheremall.core.resources.products.OptionsResourceImpl;
import com.spheremall.core.resources.products.PriceConfigurationResource;
import com.spheremall.core.resources.products.PriceConfigurationResourceImpl;
import com.spheremall.core.resources.products.ProductAttributeValuesResource;
import com.spheremall.core.resources.products.ProductAttributeValuesResourceImpl;
import com.spheremall.core.resources.products.ProductResource;
import com.spheremall.core.resources.products.ProductResourceImpl;
import com.spheremall.core.resources.shop.BasketResource;
import com.spheremall.core.resources.shop.BasketResourceImpl;
import com.spheremall.core.resources.shop.CurrenciesRateResource;
import com.spheremall.core.resources.shop.CurrenciesRateResourceImpl;
import com.spheremall.core.resources.shop.CurrenciesResource;
import com.spheremall.core.resources.shop.CurrenciesResourceImpl;
import com.spheremall.core.resources.shop.DeliveryPaymentsResource;
import com.spheremall.core.resources.shop.DeliveryPaymentsResourceImpl;
import com.spheremall.core.resources.shop.DeliveryProvidersResource;
import com.spheremall.core.resources.shop.DeliveryProvidersResourceImpl;
import com.spheremall.core.resources.shop.InvoicesResource;
import com.spheremall.core.resources.shop.InvoicesResourceImpl;
import com.spheremall.core.resources.shop.OrderItemsResource;
import com.spheremall.core.resources.shop.OrderItemsResourceImpl;
import com.spheremall.core.resources.shop.OrderStatusesResource;
import com.spheremall.core.resources.shop.OrderStatusesResourceImpl;
import com.spheremall.core.resources.shop.OrdersResource;
import com.spheremall.core.resources.shop.OrdersResourceImpl;
import com.spheremall.core.resources.shop.PaymentMethodsResource;
import com.spheremall.core.resources.shop.PaymentMethodsResourceImpl;
import com.spheremall.core.resources.shop.PaymentProvidersResource;
import com.spheremall.core.resources.shop.PaymentProvidersResourceImpl;
import com.spheremall.core.resources.shop.PromotionsResource;
import com.spheremall.core.resources.shop.PromotionsResourceImpl;
import com.spheremall.core.resources.shop.VatsResource;
import com.spheremall.core.resources.shop.VatsResourceImpl;
import com.spheremall.core.resources.users.AddressResource;
import com.spheremall.core.resources.users.AddressResourceImpl;
import com.spheremall.core.resources.users.AuthResource;
import com.spheremall.core.resources.users.AuthResourceImpl;
import com.spheremall.core.resources.users.CompaniesResource;
import com.spheremall.core.resources.users.CompaniesResourceImpl;
import com.spheremall.core.resources.users.MessagesResource;
import com.spheremall.core.resources.users.MessagesResourceImpl;
import com.spheremall.core.resources.users.UserResource;
import com.spheremall.core.resources.users.UserResourceImpl;
import com.spheremall.core.resources.users.WishListItemsResource;
import com.spheremall.core.resources.users.WishListItemsResourceImpl;
import com.spheremall.core.shop.Basket;

import java.io.IOException;

public interface ServiceInjector {

    class Store {
        private static Basket basket = null;
    }

    //region [Products]
    default AttributeDisplayTypesResource attributeDisplayTypes() {
        return new AttributeDisplayTypesResourceImpl((SMClient) this);
    }

    default AttributeTypesResource attributeTypes() {
        return new AttributeTypesResourceImpl((SMClient) this);
    }

    default AttributeGroupsEntitiesResource attributeGroupsEntities() {
        return new AttributeGroupsEntitiesResourceImpl((SMClient) this);
    }

    default AttributesResource attributes() {
        return new AttributesResourceImpl((SMClient) this);
    }

    default AttributeGroupsResource attributeGroups() {
        return new AttributeGroupsResourceImpl((SMClient) this);
    }

    default AttributeValuesResource attributeValues() {
        return new AttributeValuesResourceImpl((SMClient) this);
    }

    default FunctionalNamesResource functionalNames() {
        return new FunctionalNamesResourceImpl((SMClient) this);
    }

    default ProductResource products() {
        return new ProductResourceImpl((SMClient) this);
    }

    default PriceConfigurationResource priceConfigurations() {
        return new PriceConfigurationResourceImpl((SMClient) this);
    }

    default ProductPriceConfigurationsResource productPriceConfiguration() {
        return new ProductPriceConfigurationsResourceImpl((SMClient) this);
    }

    default BrandsResource brands() {
        return new BrandsResourceImpl((SMClient) this);
    }

    default MediaResource media() {
        return new MediaResourceImpl((SMClient) this);
    }

    default MediaTypesResource mediaTypes() {
        return new MediaTypesResourceImpl((SMClient) this);
    }

    default ProductAttributeValuesResource productAttributeValues() {
        return new ProductAttributeValuesResourceImpl((SMClient) this);
    }

    default EntitiesResource entities() {
        return new EntitiesResourceImpl((SMClient) this);
    }

    default EntityAttributesResource entityAttributes() {
        return new EntityAttributesResourceImpl((SMClient) this);
    }

    default OptionsResource options() {
        return new OptionsResourceImpl((SMClient) this);
    }

    default CatalogItemsResource catalogItems() {
        return new CatalogItemsResourceImpl((SMClient) this);
    }
    //endregion

    //region [Users]

    default UserResource users() {
        return new UserResourceImpl((SMClient) this);
    }

    default AddressResource addresses() {
        return new AddressResourceImpl((SMClient) this);
    }

    default CompaniesResource companies() {
        return new CompaniesResourceImpl((SMClient) this);
    }

    default MessagesResource messages() {
        return new MessagesResourceImpl((SMClient) this);
    }

    default WishListItemsResource wishListItems() {
        return new WishListItemsResourceImpl((SMClient) this);
    }

    default AuthResource auth() {
        return new AuthResourceImpl((SMClient) this);
    }

    //endregion

    //region [Shop]
    default BasketResource basketResource() {
        return new BasketResourceImpl((SMClient) this, "v1");
    }

    default Basket basket() throws SphereMallException, IOException {
        if (Store.basket == null) {
            Store.basket = new Basket((SMClient) this, "v1");
        }
        return Store.basket;
    }

    default Basket basket(int basketId) throws SphereMallException, IOException {
        if (Store.basket == null || Store.basket.getId() != basketId) {
            Store.basket = new Basket((SMClient) this, basketId, "v1");
        }
        return Store.basket;
    }

    default Basket basket(int basketId, int userId) throws SphereMallException, IOException {
        if (Store.basket == null || Store.basket.getId() != basketId || Store.basket.getUserId() != userId) {
            Store.basket = new Basket((SMClient) this, basketId, userId, "v1");
        }
        return Store.basket;
    }

    default void clearBasket() {
        Store.basket = null;
    }

    default CurrenciesRateResource currenciesRate() {
        return new CurrenciesRateResourceImpl((SMClient) this);
    }

    default CurrenciesResource currencies() {
        return new CurrenciesResourceImpl((SMClient) this);
    }

    default DeliveryPaymentsResource deliveryPayments() {
        return new DeliveryPaymentsResourceImpl((SMClient) this);
    }

    default DeliveryProvidersResource deliveryProviders() {
        return new DeliveryProvidersResourceImpl((SMClient) this);
    }

    default InvoicesResource invoices() {
        return new InvoicesResourceImpl((SMClient) this);
    }

    default OrderItemsResource orderItems() {
        return new OrderItemsResourceImpl((SMClient) this);
    }

    default OrdersResource orders() {
        return new OrdersResourceImpl((SMClient) this);
    }

    default OrderStatusesResource orderStatuses() {
        return new OrderStatusesResourceImpl((SMClient) this);
    }

    default PaymentMethodsResource paymentMethods() {
        return new PaymentMethodsResourceImpl((SMClient) this);
    }

    default PaymentProvidersResource paymentProviders() {
        return new PaymentProvidersResourceImpl((SMClient) this);
    }

    default VatsResource vats() {
        return new VatsResourceImpl((SMClient) this);
    }

    default PromotionsResource promotions() {
        return new PromotionsResourceImpl((SMClient) this);
    }

    //endregion

    //region [Grapher service]
    default GridResource grid() {
        return new GridResourceImpl((SMClient) this);
    }

    default CorrelationsResource correlations() {
        return new CorrelationsResourceImpl((SMClient) this);
    }
    //endregion

    //region [Documents]
    default DocumentsResource documents() {
        return new DocumentsResourceImpl((SMClient) this);
    }
    //endregion

    //region [ElasticSearch]
    default ElasticSearchResource elasticSearch() {
        return new ElasticSearchResourceImpl((SMClient) this);
    }
    //endregion
}