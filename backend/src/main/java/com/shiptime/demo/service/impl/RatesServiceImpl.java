package com.shiptime.demo.service.impl;

import com.shiptime.demo.exception.BusinessException;
import com.shiptime.demo.model.request.RatesRequest;
import com.shiptime.demo.model.RedisRateModel;
import com.shiptime.demo.model.response.RatesResponse;
import com.shiptime.demo.model.service.request.RatesServiceRequest;
import com.shiptime.demo.model.service.response.RateServiceResponse;
import com.shiptime.demo.model.service.response.RatesServiceResponse;
import com.shiptime.demo.service.RatesService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static com.shiptime.demo.util.Constant.*;

@Service
public class RatesServiceImpl implements RatesService {
    private final RestTemplate restTemplate;
    private final RedisTemplate<String, Object> redisTemplate;

    public RatesServiceImpl(final RestTemplate restTemplate, RedisTemplate<String, Object> redisTemplate){
        this.restTemplate = restTemplate;
        this.redisTemplate = redisTemplate;
    }

    public List<RatesResponse> getRates(RatesRequest ratesInput) throws Exception {
        List<RatesResponse> response = new ArrayList<>();

        URI uri = new URI(BASE_URL + "/rates/");
        RatesServiceRequest request = new RatesServiceRequest();
        request.setFrom(ratesInput.getFrom());
        request.setTo(ratesInput.getTo());
        request.setLineItems(Collections.singletonList(ratesInput.getLineItem()));
        request.setPackageType(PACKAGE_TYPE);
        request.setUnitOfMeasurement(UNIT_OF_MEASUREMENT);
        request.setShipDate(LocalDate.now().plusDays(3).format(FORMATTER));
        ResponseEntity<RatesServiceResponse> serviceResponse = restTemplate.postForEntity(uri, request, RatesServiceResponse.class);
        RatesServiceResponse ratesServiceResponse = serviceResponse.getBody();
        if(ratesServiceResponse == null || ratesServiceResponse.getAvailableRates() == null) {
            throw new BusinessException("Service has no response right now!");
        }

        List<RateServiceResponse> listRateServiceResponse = ratesServiceResponse.getAvailableRates();
        for(RateServiceResponse rateServiceResponse: listRateServiceResponse){
            RatesResponse ratesResponse = new RatesResponse();
            String id = UUID.randomUUID().toString();
            ratesResponse.setId(id);
            ratesResponse.setPrice(rateServiceResponse.getBaseCharge().getAmount());
            ratesResponse.setCurrency(rateServiceResponse.getBaseCharge().getCurrency());
            ratesResponse.setServiceName(rateServiceResponse.getServiceName());
            ratesResponse.setCarrierName(rateServiceResponse.getCarrierName());
            response.add(ratesResponse);

            RedisRateModel redisRateInput = new RedisRateModel(id, rateServiceResponse, request);
            redisTemplate.opsForValue().set(id, redisRateInput);
        }
        return response;
    }
}
