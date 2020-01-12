package com.challenge.demo.util;

import com.challenge.demo.service.entity.DeviceRequest;

/**
 * Created by nurisezgin on 12.01.2020.
 */
public class DeviceRequestCreator {

    public static DeviceRequest newDeviceRequest() {
        return newDeviceRequest("");
    }

    public static DeviceRequest newDeviceRequest(String prefix) {
        return DeviceRequest.builder()
                .brand(prefix + "brand")
                .model(prefix + "model")
                .os("iOS")
                .osVersion(prefix + "1.1.0")
                .build();
    }

}
