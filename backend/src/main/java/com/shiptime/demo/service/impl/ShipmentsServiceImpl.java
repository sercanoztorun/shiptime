package com.shiptime.demo.service.impl;

import com.shiptime.demo.exception.BusinessException;
import com.shiptime.demo.model.RedisRateModel;
import com.shiptime.demo.model.service.request.PickupDetailServiceRequest;
import com.shiptime.demo.model.response.ShipmentsResponse;
import com.shiptime.demo.model.service.request.RatesServiceRequest;
import com.shiptime.demo.model.service.request.ShipmentsServiceRequest;
import com.shiptime.demo.model.service.response.RateServiceResponse;
import com.shiptime.demo.service.ShipmentsService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.time.LocalTime;
import java.util.Date;

import static com.shiptime.demo.util.Constant.*;

@Service
public class ShipmentsServiceImpl implements ShipmentsService {

    private final RestTemplate restTemplate;
    private final RedisTemplate<String, Object> redisTemplate;

    public ShipmentsServiceImpl(RestTemplate restTemplate, RedisTemplate<String, Object> redisTemplate){
        this.restTemplate = restTemplate;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public ShipmentsResponse createShipment(String id) throws Exception {
        RedisRateModel redisRateModel = (RedisRateModel) redisTemplate.opsForValue().get(id);
        if(redisRateModel == null){
            throw new BusinessException("Wrong id value!");
        }

        URI uri = new URI(BASE_URL + "/shipments/");
        ShipmentsServiceRequest shipmentsServiceRequest = new ShipmentsServiceRequest();
        RatesServiceRequest ratesServiceRequest = redisRateModel.getRatesServiceRequest();
        shipmentsServiceRequest.setRateRequest(ratesServiceRequest);
        RateServiceResponse rateServiceResponse = redisRateModel.getRatesServiceResponse();
        shipmentsServiceRequest.setServiceId(rateServiceResponse.getServiceId());
        shipmentsServiceRequest.setCarrierId(rateServiceResponse.getCarrierId());
        shipmentsServiceRequest.setQuoteId(rateServiceResponse.getQuoteId());
        PickupDetailServiceRequest pickupDetailRequest = new PickupDetailServiceRequest();
        pickupDetailRequest.setPickupDate(ratesServiceRequest.getShipDate());
        pickupDetailRequest.setLocation(SHIPPING_LOCATION);
        LocalTime cutOffTime = LocalTime.parse( rateServiceResponse.getCutoffTime());
        if(rateServiceResponse.getAccessTimeInterval().length() == 4){
            rateServiceResponse.setAccessTimeInterval("0" + rateServiceResponse.getAccessTimeInterval());
        }
        LocalTime accessTimeInterval = LocalTime.parse(rateServiceResponse.getAccessTimeInterval());
        accessTimeInterval = cutOffTime.minusHours(accessTimeInterval.getHour());
        pickupDetailRequest.setReadyTime(accessTimeInterval.toString());
        pickupDetailRequest.setCloseTime(rateServiceResponse.getCutoffTime());
        shipmentsServiceRequest.setPickupDetail(pickupDetailRequest);

        ResponseEntity<ShipmentsResponse> shipmentsServiceResponse = restTemplate.postForEntity(uri, shipmentsServiceRequest, ShipmentsResponse.class);
        ShipmentsResponse shipmentsResponse = shipmentsServiceResponse.getBody();
        if(shipmentsResponse == null){
            throw new BusinessException("Service has no response right now!");
        }
        return shipmentsResponse;
    }
}
