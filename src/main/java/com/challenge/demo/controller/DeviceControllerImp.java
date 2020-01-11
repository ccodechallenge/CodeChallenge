package com.challenge.demo.controller;

import com.challenge.demo.service.DeviceService;
import com.challenge.demo.service.entity.DeviceRequest;
import com.challenge.demo.service.entity.DeviceResponse;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by nurisezgin on 11.01.2020.
 */
@RestController
@Api(tags = "Device API", description = "APIs for adding a new device to store or listing recorded devices in store.")
public class DeviceControllerImp implements DeviceController {

    @Autowired
    private DeviceService service;

    @Override
    public DeviceResponse putDevice(@RequestBody DeviceRequest data) {
        return service.putDevice(data);
    }

    @Override
    public List<DeviceResponse> getDevices(String brand, String model, String os, String osVersion, Pageable pageable) {
        return service.getDevices(brand, model, os, osVersion, pageable);
    }
}
