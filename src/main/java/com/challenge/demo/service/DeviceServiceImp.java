package com.challenge.demo.service;

import com.challenge.demo.repository.DeviceRepository;
import com.challenge.demo.repository.entity.Device;
import com.challenge.demo.service.entity.DeviceRequest;
import com.challenge.demo.service.entity.DeviceResponse;
import com.challenge.demo.service.mapper.FromDevice;
import com.challenge.demo.service.mapper.FromDeviceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by nurisezgin on 11.01.2020.
 */
@Service
public class DeviceServiceImp implements DeviceService {

    @Autowired
    private DeviceRepository repository;

    @Override
    public DeviceResponse putDevice(DeviceRequest data) {
        Device device = new FromDeviceRequest(data)
                .toDevice();

        Device recorded = repository.save(device);

        return toDeviceResponse(recorded);
    }

    private DeviceResponse toDeviceResponse(Device device) {
        return new FromDevice(device)
                .toResponse();
    }

}
