package com.shiptime.demo.service.impl;

import com.shiptime.demo.exception.BusinessException;
import com.shiptime.demo.model.RedisRateModel;
import com.shiptime.demo.model.response.ShipmentsResponse;
import com.shiptime.demo.model.service.request.AddressServiceRequest;
import com.shiptime.demo.model.service.request.LineItemServiceRequest;
import com.shiptime.demo.model.service.request.RatesServiceRequest;
import com.shiptime.demo.model.service.response.RateServiceResponse;
import com.shiptime.demo.service.ShipmentsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ShipmentsServiceImplTest {
    @Mock
    private RestTemplate restTemplate;

    @Mock
    private RedisTemplate<String, Object> redisTemplate;

    @Mock
    private ValueOperations valueOperations;

    ShipmentsService service;



    @BeforeEach
    void setUp() {
        service = new ShipmentsServiceImpl(restTemplate, redisTemplate);
    }

    @Test
    public void testCreateShipmentWrongId(){
        String id = "123";
        Mockito.when(redisTemplate.opsForValue()).thenReturn(valueOperations);
        Mockito.when(redisTemplate.opsForValue().get(Mockito.any())).thenReturn(null);
        BusinessException exception = assertThrows(BusinessException.class, () -> service.createShipment(id));
        assert(exception.getMessage()).equals("Wrong id value!");
    }
    @Test
    public void testCreateShipmentServiceNoResponse(){
        String id = "123";
        RedisRateModel redisRateModel = new RedisRateModel();

        RatesServiceRequest rateServiceRequest = new RatesServiceRequest();
        rateServiceRequest.setShipDate("2023-12-10");
        rateServiceRequest.setUnitOfMeasurement("METRIC");
        rateServiceRequest.setPackageType("PACKAGE");
        AddressServiceRequest addressServiceRequest = new AddressServiceRequest();
        addressServiceRequest.setAttention("Sercan");
        addressServiceRequest.setCity("North York");
        addressServiceRequest.setPhone("647 981 9691");
        addressServiceRequest.setState("ON");
        addressServiceRequest.setPostalCode("M2H2S9");
        addressServiceRequest.setCompanyName("Sercan");
        rateServiceRequest.setFrom(addressServiceRequest);
        rateServiceRequest.setTo(addressServiceRequest);
        LineItemServiceRequest lineItemServiceRequest = new LineItemServiceRequest();
        lineItemServiceRequest.setHeight(10.0);
        lineItemServiceRequest.setLength(10.0);
        lineItemServiceRequest.setWidth(10.0);
        lineItemServiceRequest.setWeight(2.0);
        rateServiceRequest.setLineItems(Collections.singletonList(lineItemServiceRequest));

        RateServiceResponse rateServiceResponse = new RateServiceResponse();
        rateServiceResponse.setAccessTimeInterval("02:00");
        rateServiceResponse.setCutoffTime("10:00");
        rateServiceResponse.setServiceId("1234");
        rateServiceResponse.setCarrierId("1234");
        rateServiceResponse.setQuoteId("1234");


        redisRateModel.setRatesServiceResponse(rateServiceResponse);
        redisRateModel.setRatesServiceRequest(rateServiceRequest);
        Mockito.when(redisTemplate.opsForValue()).thenReturn(valueOperations);
        Mockito.when(redisTemplate.opsForValue().get(Mockito.any())).thenReturn(redisRateModel);

        ResponseEntity<ShipmentsResponse> responseEntity = ResponseEntity.of(Optional.empty());
        Mockito.when(restTemplate.postForEntity(Mockito.any(),Mockito.any(),Mockito.eq(ShipmentsResponse.class))).thenReturn(responseEntity);
        BusinessException exception = assertThrows(BusinessException.class, () -> service.createShipment(id));
        assert(exception.getMessage()).equals("Service has no response right now!");
    }
    @Test
    public void testCreateShipmentSuccess() throws Exception {
        String id = "123";
        RedisRateModel redisRateModel = new RedisRateModel();

        RatesServiceRequest rateServiceRequest = new RatesServiceRequest();
        rateServiceRequest.setShipDate("2023-12-10");
        rateServiceRequest.setUnitOfMeasurement("METRIC");
        rateServiceRequest.setPackageType("PACKAGE");
        AddressServiceRequest addressServiceRequest = new AddressServiceRequest();
        addressServiceRequest.setAttention("Sercan");
        addressServiceRequest.setCity("North York");
        addressServiceRequest.setPhone("647 981 9691");
        addressServiceRequest.setState("ON");
        addressServiceRequest.setPostalCode("M2H2S9");
        addressServiceRequest.setCompanyName("Sercan");
        rateServiceRequest.setFrom(addressServiceRequest);
        rateServiceRequest.setTo(addressServiceRequest);
        LineItemServiceRequest lineItemServiceRequest = new LineItemServiceRequest();
        lineItemServiceRequest.setHeight(10.0);
        lineItemServiceRequest.setLength(10.0);
        lineItemServiceRequest.setWidth(10.0);
        lineItemServiceRequest.setWeight(2.0);
        rateServiceRequest.setLineItems(Collections.singletonList(lineItemServiceRequest));

        RateServiceResponse rateServiceResponse = new RateServiceResponse();
        rateServiceResponse.setAccessTimeInterval("02:00");
        rateServiceResponse.setCutoffTime("10:00");
        rateServiceResponse.setServiceId("1234");
        rateServiceResponse.setCarrierId("1234");
        rateServiceResponse.setQuoteId("1234");

        redisRateModel.setRatesServiceResponse(rateServiceResponse);
        redisRateModel.setRatesServiceRequest(rateServiceRequest);
        Mockito.when(redisTemplate.opsForValue()).thenReturn(valueOperations);
        Mockito.when(redisTemplate.opsForValue().get(Mockito.any())).thenReturn(redisRateModel);

        ShipmentsResponse shipmentsResponse = new ShipmentsResponse();
        shipmentsResponse.setShipId(123L);
        shipmentsResponse.setLabelUrl("http://restapi.appspaces.ca/rest/shipments//4826146/label");
        shipmentsResponse.setTrackingNumbers(Collections.singletonList("D420917200000003624001"));
        ResponseEntity<ShipmentsResponse> responseEntity = ResponseEntity.of(Optional.of(shipmentsResponse));
        Mockito.when(restTemplate.postForEntity(Mockito.any(),Mockito.any(),Mockito.eq(ShipmentsResponse.class))).thenReturn(responseEntity);
        ShipmentsResponse shipmentsResponse1 = service.createShipment(id);
        assert(shipmentsResponse1.getLabelUrl()).equals("http://restapi.appspaces.ca/rest/shipments//4826146/label");
        assert(shipmentsResponse1.getShipId()).equals(123L);
    }
}