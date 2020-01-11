package com.challenge.demo.controller;

import com.challenge.demo.service.entity.DeviceRequest;
import com.challenge.demo.service.entity.DeviceResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

/**
 * Created by nurisezgin on 11.01.2020.
 */

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DeviceControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void should_PutDeviceCorrect() {
        DeviceRequest data = DeviceRequest.builder()
                .brand("brand")
                .model("model")
                .os("iOS")
                .osVersion("1.1.0-alpha")
                .build();

        HttpEntity<DeviceRequest> request = new HttpEntity<>(data);
        String path = "http://localhost:" + port + "/device";
        ResponseEntity<DeviceResponse> response = restTemplate.exchange(path,
                HttpMethod.PUT, request, DeviceResponse.class);

        assertThat(response.getBody().getId(), greaterThan(0L));
    }

    @Test
    public void should_GetDeviceListCorrect() {
        String path = "http://localhost:" + port + "/devices";
        ResponseEntity<Object> response = restTemplate.exchange(path,
                HttpMethod.GET, null, Object.class);

        List<DeviceResponse> devices = ((List<DeviceResponse>) response.getBody());

        assertThat(devices.size(), greaterThan(0));
    }

    @Test
    public void should_GetSingleFilteredDeviceListCorrect() {
        String path = "http://localhost:" + port + "/devices" + "?" + "brand=" + "Samsung";
        ResponseEntity<Object> response = restTemplate.exchange(path,
                HttpMethod.GET, null, Object.class);

        List<DeviceResponse> devices = ((List<DeviceResponse>) response.getBody());

        assertThat(devices.size(), greaterThan(0));
    }

    @Test
    public void should_GetMultipleFilteredDeviceListCorrect() {
        String path = "http://localhost:" + port + "/devices" + "?" + "brand=" + "Samsung" + "&os=" + "Android";
        ResponseEntity<Object> response = restTemplate.exchange(path,
                HttpMethod.GET, null, Object.class);

        List<DeviceResponse> devices = ((List<DeviceResponse>) response.getBody());

        assertThat(devices.size(), greaterThan(0));
    }
}