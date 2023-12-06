package com.shiptime.demo.model.service.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class ChargeServiceResponse implements Serializable {
    private String currency;
    private Double amount;
}
