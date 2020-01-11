package com.challenge.demo.service.mapper;

import com.challenge.demo.repository.entity.Device;
import com.challenge.demo.service.entity.DeviceRequest;

public class FromDeviceRequest {

    private final DeviceRequest data;

    public FromDeviceRequest(DeviceRequest data) {
        if (data == null) {
            throw new NullPointerException("Given device is null.");
        }

        this.data = data;
    }

    public Device toDevice() {
        return Device.builder()
                .brand(data.getBrand())
                .model(data.getModel())
                .os(data.getOs())
                .osVersion(data.getOsVersion())
                .build();
    }
}
