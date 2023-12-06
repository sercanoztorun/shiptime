package com.shiptime.demo.controller;

import com.shiptime.demo.model.request.RatesRequest;
import com.shiptime.demo.model.response.RatesResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/rates")
public interface RatesController {
    @CrossOrigin
    @PostMapping()
    ResponseEntity<List<RatesResponse>> getRates(@RequestBody RatesRequest ratesInput) throws Exception;

}
