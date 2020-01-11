package com.challenge.demo.controller;

import com.challenge.demo.service.entity.DeviceRequest;
import com.challenge.demo.service.entity.DeviceResponse;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Created by nurisezgin on 11.01.2020.
 */
public interface DeviceController {

    @PutMapping("/device")
    DeviceResponse putDevice(@RequestBody DeviceRequest data);

}
