package com.shiptime.demo.model.response;

import lombok.Data;

import java.util.List;

@Data
public class ShipmentsResponse {
    private Long shipId;
    private List<String> trackingNumbers;
    private String labelUrl;
    private String pickupConfirmation;

}
