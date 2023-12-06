package com.shiptime.demo.model.service.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class RateServiceResponse implements Serializable {
    private ChargeServiceResponse baseCharge;
    private ChargeServiceResponse totalCharge;
    private ChargeServiceResponse totalBeforeTaxes;
    private String carrierId;
    private String carrierName;
    private String serviceId;
    private String serviceName;
    private Integer transitDays;
    private String cutoffTime;
    private String accessTimeInterval;
    private String serviceTerms;
    private String quoteId;

}
