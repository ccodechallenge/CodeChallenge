package com.challenge.demo.service.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DeviceRequest {

    private String model;
    private String brand;
    private String os;
    private String osVersion;

}
