package com.shiptime.demo.model;

import com.shiptime.demo.model.service.request.RatesServiceRequest;
import com.shiptime.demo.model.service.response.RateServiceResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RedisRateModel implements Serializable {
    private String id;
    private RateServiceResponse ratesServiceResponse;
    private RatesServiceRequest ratesServiceRequest;

}
