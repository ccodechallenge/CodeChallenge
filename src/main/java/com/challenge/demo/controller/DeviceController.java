package com.challenge.demo.controller;

import com.challenge.demo.service.entity.DeviceRequest;
import com.challenge.demo.service.entity.DeviceResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by nurisezgin on 11.01.2020.
 */
public interface DeviceController {

    @PutMapping("/device")
    DeviceResponse putDevice(@RequestBody DeviceRequest data);

    @GetMapping("/devices")
    List<DeviceResponse> getDevices(
            @RequestParam(name = "brand", required = false) String brand,
            @RequestParam(name = "model", required = false) String model,
            @RequestParam(name = "os", required = false) String os,
            @RequestParam(name = "osVersion", required = false) String osVersion,
            @SortDefault.SortDefaults(@SortDefault(sort = "id", direction = Sort.Direction.ASC))
                    Pageable pageable);
}
