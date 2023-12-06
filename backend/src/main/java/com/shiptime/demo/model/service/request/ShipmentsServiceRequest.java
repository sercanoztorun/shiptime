package com.shiptime.demo.model.service.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class ShipmentsServiceRequest implements Serializable {
    private RatesServiceRequest rateRequest;
    private String carrierId;
    private String serviceId;
    private String quoteId;
    private PickupDetailServiceRequest pickupDetail;

}
