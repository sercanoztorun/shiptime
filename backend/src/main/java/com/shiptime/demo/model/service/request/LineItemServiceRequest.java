package com.shiptime.demo.model.service.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class LineItemServiceRequest implements Serializable {
    private Double length;
    private Double width;
    private Double height;
    private Double weight;

}
