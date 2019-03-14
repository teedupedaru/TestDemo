package com.luminor.dc.testautomation.api.model;

import java.util.List;

public class PaymentsResponse {

    public List<PaymentsGroup> processing;
    public List<PaymentsGroup> pending;
    public List<PaymentsGroup> rejected;
}
