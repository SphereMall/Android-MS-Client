package com.spheremall.core.shop;

public interface PaymentStatus {
    String IN_PROGRESS = "1";
    String SUCCESS = "2";
    String FAIL = "3";
    String CANCELED = "4";
    String PENDING = "5";
    String REFUSED = "6";
    String REFUND = "7";
}
