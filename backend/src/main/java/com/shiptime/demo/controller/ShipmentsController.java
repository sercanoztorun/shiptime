package com.shiptime.demo.controller;

import com.shiptime.demo.model.request.ShipmentsRequest;
import com.shiptime.demo.model.response.RatesResponse;
import com.shiptime.demo.model.response.ShipmentsResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RequestMapping("/shipments")
public interface ShipmentsController {
    @CrossOrigin
    @PostMapping()
    ResponseEntity<ShipmentsResponse> createShipment(@RequestBody ShipmentsRequest id) throws Exception;

}
