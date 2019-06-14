package com.spheremall.core.shop;

public interface PaymentStatus {
    String IN_PROGRESS = "1";
    String SUCCESS = "2";
    String FAIL = "3";
    String CANCELED = "4";
    String PENDING = "4";
    String REFUSED = "5";
    String REFUND = "6";
}
