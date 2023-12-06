package com.shiptime.demo.controller.impl;
import com.shiptime.demo.controller.ShipmentsController;
import com.shiptime.demo.model.request.ShipmentsRequest;
import com.shiptime.demo.model.response.ShipmentsResponse;
import com.shiptime.demo.service.ShipmentsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ShipmentsControllerImpl implements ShipmentsController {
    public ShipmentsService shipmentsService;

    public ShipmentsControllerImpl(ShipmentsService shipmentsService) {
        this.shipmentsService = shipmentsService;
    }

    @Override
    public ResponseEntity<ShipmentsResponse> createShipment(ShipmentsRequest input) throws Exception {
        ShipmentsResponse shipmentsResponse = shipmentsService.createShipment(input.getId());
        return new ResponseEntity<>(shipmentsResponse, HttpStatus.OK);
    }

}
