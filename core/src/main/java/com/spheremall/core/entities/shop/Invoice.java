package com.spheremall.core.entities.shop;

import com.spheremall.core.entities.Entity;
import com.spheremall.core.jsonapi.annotations.Type;

@Type("invoices")
public class Invoice extends Entity {

    public String invoiceHash;
    public String path;
    public String createDate;
    public int orderId;
}
