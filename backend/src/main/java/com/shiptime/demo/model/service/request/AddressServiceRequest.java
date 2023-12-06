package com.shiptime.demo.model.service.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class AddressServiceRequest implements Serializable {
    private String companyName;
    private String streetAddress;
    private String city;
    private String countryCode;
    private String state;
    private String postalCode;
    private String attention;
    private String phone;

}
