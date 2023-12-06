package com.shiptime.demo.model.service.request;

import lombok.Data;

@Data
public class PickupDetailServiceRequest {
    private String location;
    private String pickupDate;
    private String readyTime;
    private String closeTime;
}
