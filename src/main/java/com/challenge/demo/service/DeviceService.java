package com.challenge.demo.service;

import com.challenge.demo.service.entity.DeviceRequest;
import com.challenge.demo.service.entity.DeviceResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by nurisezgin on 11.01.2020.
 */
public interface DeviceService {

    DeviceResponse putDevice(DeviceRequest data);

    List<DeviceResponse> getDevices(String brand, String model, String os, String osVersion, Pageable pageable);
}
