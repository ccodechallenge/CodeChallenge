package com.challenge.demo.service;

import com.challenge.demo.repository.DeviceRepository;
import com.challenge.demo.repository.entity.Device;
import com.challenge.demo.service.entity.DeviceRequest;
import com.challenge.demo.service.entity.DeviceResponse;
import com.challenge.demo.service.mapper.FromDevice;
import com.challenge.demo.service.mapper.FromDeviceRequest;
import com.challenge.demo.service.specification.DeviceSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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

    @Override
    public List<DeviceResponse> getDevices(String brand, String model, String os, String osVersion, Pageable pageable) {
        Device desired = Device.builder()
                .brand(brand)
                .model(model)
                .os(os)
                .osVersion(osVersion)
                .build();

        DeviceSpecification specs = DeviceSpecification.with(desired);
        Page<Device> result = repository.findAll(specs, pageable);

        return StreamSupport.stream(result.spliterator(), false)
                .map(this::toDeviceResponse)
                .collect(Collectors.toList());
    }

    private DeviceResponse toDeviceResponse(Device device) {
        return new FromDevice(device)
                .toResponse();
    }

}
