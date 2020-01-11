package com.challenge.demo.controller;

import com.challenge.demo.service.DeviceService;
import com.challenge.demo.service.entity.DeviceRequest;
import com.challenge.demo.service.entity.DeviceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by nurisezgin on 11.01.2020.
 */
@RestController
public class DeviceControllerImp implements DeviceController {

    @Autowired
    private DeviceService service;

    @Override
    public DeviceResponse putDevice(@RequestBody DeviceRequest data) {
        return service.putDevice(data);
    }
}
