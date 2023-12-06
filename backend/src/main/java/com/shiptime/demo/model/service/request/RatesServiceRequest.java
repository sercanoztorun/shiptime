package com.shiptime.demo.model.service.request;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class RatesServiceRequest implements Serializable {
    private AddressServiceRequest from;
    private AddressServiceRequest to;
    private List<LineItemServiceRequest> lineItems;
    private String packageType;
    private String unitOfMeasurement;
    private String shipDate;
}
