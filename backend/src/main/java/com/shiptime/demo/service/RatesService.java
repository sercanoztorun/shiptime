package com.shiptime.demo.service;

import com.shiptime.demo.model.request.RatesRequest;
import com.shiptime.demo.model.response.RatesResponse;

import java.util.List;

public interface RatesService {
    List<RatesResponse> getRates(RatesRequest ratesInput) throws Exception;
}
