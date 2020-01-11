package com.challenge.demo.service;

import com.challenge.demo.service.entity.DeviceRequest;
import com.challenge.demo.service.entity.DeviceResponse;

/**
 * Created by nurisezgin on 11.01.2020.
 */
public interface DeviceService {

    DeviceResponse putDevice(DeviceRequest data);

}
