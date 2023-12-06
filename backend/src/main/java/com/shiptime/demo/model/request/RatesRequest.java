package com.shiptime.demo.model.request;

import com.shiptime.demo.model.service.request.AddressServiceRequest;
import com.shiptime.demo.model.service.request.LineItemServiceRequest;
import lombok.Data;

@Data
public class RatesRequest {
    private AddressServiceRequest from;
    private AddressServiceRequest to;
    private LineItemServiceRequest lineItem;
}
