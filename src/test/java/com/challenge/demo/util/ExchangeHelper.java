package com.challenge.demo.util;

import com.challenge.demo.service.entity.DeviceRequest;
import com.challenge.demo.service.entity.DeviceResponse;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * Created by nurisezgin on 12.01.2020.
 */
public final class ExchangeHelper {

    private final TestRestTemplate restTemplate;

    private ExchangeHelper(TestRestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public static ExchangeHelper with(TestRestTemplate restTemplate) {
        return new ExchangeHelper(restTemplate);
    }

    public DeviceResponse putDevice(String path, DeviceRequest data) {
        HttpEntity<DeviceRequest> request = new HttpEntity<>(data);

        ResponseEntity<DeviceResponse> response = restTemplate.exchange(path,
                HttpMethod.PUT, request, DeviceResponse.class);

        return response.getBody();
    }

    public List<DeviceResponse> getDevices(String path) {
        ResponseEntity<Object> response = restTemplate.exchange(path,
                HttpMethod.GET, null, Object.class);

        List<DeviceResponse> devices = ((List<DeviceResponse>) response.getBody());

        return devices;
    }
}
