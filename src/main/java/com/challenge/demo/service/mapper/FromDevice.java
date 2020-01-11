package com.challenge.demo.service.mapper;

import com.challenge.demo.repository.entity.Device;
import com.challenge.demo.service.entity.DeviceResponse;

public final class FromDevice {

    private final Device data;

    public FromDevice(Device data) {
        if (data == null) {
            throw new NullPointerException("Given device is null.");
        }

        this.data = data;
    }

    public DeviceResponse toResponse() {
        return DeviceResponse.deviceResponseBuilder()
                .id(data.getId())
                .brand(data.getBrand())
                .model(data.getModel())
                .os(data.getOs())
                .osVersion(data.getOsVersion())
                .build();
    }
}
