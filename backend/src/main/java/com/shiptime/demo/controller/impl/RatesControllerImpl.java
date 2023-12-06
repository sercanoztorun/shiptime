package com.shiptime.demo.controller.impl;

import com.shiptime.demo.controller.RatesController;
import com.shiptime.demo.model.request.RatesRequest;
import com.shiptime.demo.model.response.RatesResponse;
import com.shiptime.demo.service.RatesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RatesControllerImpl implements RatesController {
    public RatesService ratesService;

    public RatesControllerImpl(RatesService ratesService){
        this.ratesService = ratesService;
    }
    @Override
    public ResponseEntity<List<RatesResponse>> getRates(RatesRequest ratesInput) throws Exception {
            System.out.println(ratesInput);
            List<RatesResponse> ratesResponse = ratesService.getRates(ratesInput);
            return new ResponseEntity<>(ratesResponse, HttpStatus.OK);
    }
}
