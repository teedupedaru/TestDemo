package com.luminor.dc.testautomation.api.model;

import com.luminor.dc.testautomation.api.model.child.Payment;

import java.util.List;

public class PaymentsGroup {

    public String name;
    public double totalAmount;
    public String currency;
    public List<Payment> payments;
}
