package com.shiptime.demo.service;

import com.shiptime.demo.model.response.RatesResponse;
import com.shiptime.demo.model.response.ShipmentsResponse;

import java.util.List;

public interface ShipmentsService {
    ShipmentsResponse createShipment(String id) throws Exception;
}
