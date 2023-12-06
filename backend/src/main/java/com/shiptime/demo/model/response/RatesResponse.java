package com.shiptime.demo.model.response;

import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
public class RatesResponse implements Serializable {
    private String id;
    private String carrierName;
    private String serviceName;
    private Double price;
    private String currency;

}
