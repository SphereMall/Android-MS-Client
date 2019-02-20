package com.spheremall.core.entities;

import com.spheremall.core.entities.documents.Document;
import com.spheremall.core.entities.documents.EntityAttributeValue;
import com.spheremall.core.entities.products.Attribute;
import com.spheremall.core.entities.products.AttributeDisplayType;
import com.spheremall.core.entities.products.AttributeGroup;
import com.spheremall.core.entities.products.AttributeGroupsEntities;
import com.spheremall.core.entities.products.AttributeType;
import com.spheremall.core.entities.products.AttributeValue;
import com.spheremall.core.entities.products.Brand;
import com.spheremall.core.entities.products.CatalogItem;
import com.spheremall.core.entities.products.EntityAttribute;
import com.spheremall.core.entities.products.FunctionalName;
import com.spheremall.core.entities.products.Media;
import com.spheremall.core.entities.products.MediaDisplayType;
import com.spheremall.core.entities.products.MediaEntity;
import com.spheremall.core.entities.products.MediaType;
import com.spheremall.core.entities.products.Option;
import com.spheremall.core.entities.products.Product;
import com.spheremall.core.entities.products.ProductAttributeValue;
import com.spheremall.core.entities.shop.BasketOrder;
import com.spheremall.core.entities.shop.Currency;
import com.spheremall.core.entities.shop.CurrencyRate;
import com.spheremall.core.entities.shop.DeliveryPaymentRelation;
import com.spheremall.core.entities.shop.DeliveryProvider;
import com.spheremall.core.entities.shop.Invoice;
import com.spheremall.core.entities.shop.Order;
import com.spheremall.core.entities.shop.OrderItem;
import com.spheremall.core.entities.shop.OrderStatus;
import com.spheremall.core.entities.shop.PaymentMethod;
import com.spheremall.core.entities.shop.PaymentProvider;
import com.spheremall.core.entities.shop.ProductsToPromotions;
import com.spheremall.core.entities.shop.Promotion;
import com.spheremall.core.entities.shop.Vat;
import com.spheremall.core.entities.users.Address;
import com.spheremall.core.entities.users.Company;
import com.spheremall.core.entities.users.Message;
import com.spheremall.core.entities.users.User;
import com.spheremall.core.entities.users.WishListItem;

import java.util.HashMap;
import java.util.Map;

public class EntitiesProvider {

    public static final HashMap<String, Class> availableEntities = new HashMap<>();

    static {
        availableEntities.put("addresses", Address.class);
        availableEntities.put("attributes", Attribute.class);
        availableEntities.put("attributeDisplayTypes", AttributeDisplayType.class);
        availableEntities.put("attributeGroups", AttributeGroup.class);
        availableEntities.put("attributeGroupsEntities", AttributeGroupsEntities.class);
        availableEntities.put("attributeTypes", AttributeType.class);
        availableEntities.put("attributeValues", AttributeValue.class);
        availableEntities.put("basket", BasketOrder.class);
        availableEntities.put("brands", Brand.class);
        availableEntities.put("catalogItems", CatalogItem.class);
        availableEntities.put("companies", Company.class);
        availableEntities.put("currencies", Currency.class);
        availableEntities.put("currenciesRate", CurrencyRate.class);
        availableEntities.put("deliverypaymentrelations", DeliveryPaymentRelation.class);
        availableEntities.put("deliveryProviders", DeliveryProvider.class);
        availableEntities.put("documents", Document.class);
        availableEntities.put("entityattributes", EntityAttribute.class);
        availableEntities.put("functionalNames", FunctionalName.class);
        availableEntities.put("invoices", Invoice.class);
        availableEntities.put("images", Media.class);
        availableEntities.put("media", Media.class);
        availableEntities.put("imageTypes", MediaType.class);
        availableEntities.put("userMessages", Message.class);
        availableEntities.put("options", Option.class);
        availableEntities.put("orders", Order.class);
        availableEntities.put("orderItems", OrderItem.class);
        availableEntities.put("orderStatus", OrderStatus.class);
        availableEntities.put("paymentMethods", PaymentMethod.class);
        availableEntities.put("paymentProviders", PaymentProvider.class);
        availableEntities.put("products", Product.class);
        availableEntities.put("productAttributeValues", ProductAttributeValue.class);
        availableEntities.put("entities", SMEntity.class);
        availableEntities.put("users", User.class);
        availableEntities.put("vat", Vat.class);
        availableEntities.put("wishListItems", WishListItem.class);
        availableEntities.put("productsToPromotions", ProductsToPromotions.class);
        availableEntities.put("promotions", Promotion.class);
        availableEntities.put("mediaEntities", MediaEntity.class);
        availableEntities.put("mediaDisplayTypes", MediaDisplayType.class);
        availableEntities.put("channelEntities", ChannelEntity.class);
        availableEntities.put("entityAttributeValues", EntityAttributeValue.class);
        availableEntities.put("range", Range.class);

    }

    private static <K, V> K getKey(Map<K, V> map, V value) {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            if (value.equals(entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }
}
