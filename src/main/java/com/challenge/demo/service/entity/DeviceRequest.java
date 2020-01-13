package com.challenge.demo.service.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public final class DeviceRequest {

    private String model;
    private String brand;
    private String os;
    private String osVersion;

}
