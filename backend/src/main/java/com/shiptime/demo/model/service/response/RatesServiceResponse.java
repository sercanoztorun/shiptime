package com.shiptime.demo.model.service.response;

import lombok.Data;

import java.util.List;
@Data
public class RatesServiceResponse {
    List<RateServiceResponse> availableRates;
}
