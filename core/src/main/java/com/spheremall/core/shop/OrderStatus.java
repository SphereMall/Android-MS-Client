package com.spheremall.core.shop;

public interface OrderStatus {
    String BASKET = "1";
    String NEW_ORDER = "2";
    String WAITING_FOR_DELIVERY = "3";
    String SHIPPED = "4";
    String CLOSED = "5";
    String RETURNED = "6";
    String CANCELED = "7";

}
