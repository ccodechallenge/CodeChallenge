package com.challenge.demo.service.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeviceResponse extends BaseResponse {

    private Long id;
    private String model;
    private String brand;
    private String os;
    private String osVersion;

    @Builder(builderMethodName = "deviceResponseBuilder")
    public DeviceResponse(Long id, String model, String brand, String os, String osVersion) {
        super(true, null);
        this.id = id;
        this.model = model;
        this.brand = brand;
        this.os = os;
        this.osVersion = osVersion;
    }
}
