package com.challenge.demo.controller;

import com.challenge.demo.service.entity.DeviceRequest;
import com.challenge.demo.service.entity.DeviceResponse;
import com.challenge.demo.util.PathBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.net.URISyntaxException;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
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

    private static final String API_EXT_ADD_DEVICE = "device";
    private static final String API_EXT_LIST_DEVICE = "devices";

    @Test
    public void should_PutDeviceCorrect() {
        final String path = PathBuilder.newBuilder(port)
                .addApiExtension(API_EXT_ADD_DEVICE)
                .build();

        DeviceRequest data = DeviceRequest.builder()
                .brand("brand")
                .model("model")
                .os("iOS")
                .osVersion("1.1.0-alpha")
                .build();

        HttpEntity<DeviceRequest> request = new HttpEntity<>(data);

        ResponseEntity<DeviceResponse> response = restTemplate.exchange(path,
                HttpMethod.PUT, request, DeviceResponse.class);

        assertThat(response.getBody().getId(), greaterThan(0L));
    }

    @Test
    public void should_GetDeviceListCorrect() {
        final String path = PathBuilder.newBuilder(port)
                .addApiExtension(API_EXT_LIST_DEVICE)
                .build();

        ResponseEntity<Object> response = restTemplate.exchange(path,
                HttpMethod.GET, null, Object.class);

        List<DeviceResponse> devices = ((List<DeviceResponse>) response.getBody());

        assertThat(devices.size(), greaterThan(0));
    }

    @Test
    public void should_GetSingleFilteredDeviceListCorrect() throws URISyntaxException {
        final String path = PathBuilder.newBuilder(port)
                .addApiExtension(API_EXT_LIST_DEVICE)
                .addQueryParam(Pair.of("brand", "Samsung"))
                .build();

        ResponseEntity<Object> response = restTemplate.exchange(path,
                HttpMethod.GET, null, Object.class);

        List<DeviceResponse> devices = ((List<DeviceResponse>) response.getBody());

        assertThat(devices.size(), greaterThan(0));
    }

    @Test
    public void should_GetMultipleFilteredDeviceListCorrect() throws URISyntaxException {
        final String path = PathBuilder.newBuilder(port)
                .addApiExtension(API_EXT_LIST_DEVICE)
                .addQueryParam(Pair.of("brand", "Samsung"))
                .addQueryParam(Pair.of("os", "Android"))
                .build();

        ResponseEntity<Object> response = restTemplate.exchange(path,
                HttpMethod.GET, null, Object.class);

        List<DeviceResponse> devices = ((List<DeviceResponse>) response.getBody());

        assertThat(devices.size(), greaterThan(0));
    }

    @Test
    public void should_FilteringRecentlyAddedDeviceCorrect() throws URISyntaxException {
        final String putPath = PathBuilder.newBuilder(port)
                .addApiExtension(API_EXT_ADD_DEVICE)
                .build();

        DeviceRequest data = DeviceRequest.builder()
                .brand("testBrand")
                .model("testModel")
                .os("iOS")
                .osVersion("1.1.0-alpha")
                .build();

        HttpEntity<DeviceRequest> request = new HttpEntity<>(data);

        restTemplate.exchange(putPath, HttpMethod.PUT, request, DeviceResponse.class);

        final String listPath = PathBuilder.newBuilder(port)
                .addApiExtension(API_EXT_LIST_DEVICE)
                .addQueryParam(Pair.of("brand", "testBrand"))
                .addQueryParam(Pair.of("model", "testModel"))
                .addQueryParam(Pair.of("osVersion", "1.1.0-alpha"))
                .build();

        ResponseEntity<Object> response = restTemplate.exchange(listPath,
                HttpMethod.GET, null, Object.class);

        List<DeviceResponse> devices = ((List<DeviceResponse>) response.getBody());

        assertThat(devices.size(), equalTo(1));
    }
}