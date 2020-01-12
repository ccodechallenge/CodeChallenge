package com.challenge.demo.controller;

import com.challenge.demo.service.entity.DeviceRequest;
import com.challenge.demo.service.entity.DeviceResponse;
import com.challenge.demo.util.ExchangeHelper;
import com.challenge.demo.util.PathBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.data.util.Pair;

import java.net.URISyntaxException;
import java.util.List;

import static com.challenge.demo.util.DeviceRequestCreator.newDeviceRequest;
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

        DeviceResponse response = ExchangeHelper.with(restTemplate)
                .putDevice(path, newDeviceRequest("put"));

        assertThat(response.getId(), greaterThan(0L));
    }

    @Test
    public void should_GetDeviceListCorrect() {
        final String path = PathBuilder.newBuilder(port)
                .addApiExtension(API_EXT_LIST_DEVICE)
                .build();

        List<DeviceResponse> devices = ExchangeHelper.with(restTemplate)
                .getDevices(path);

        assertThat(devices.size(), greaterThan(0));
    }

    @Test
    public void should_GetSingleFilteredDeviceListCorrect() throws URISyntaxException {
        final String path = PathBuilder.newBuilder(port)
                .addApiExtension(API_EXT_LIST_DEVICE)
                .addQueryParam(Pair.of("brand", "Samsung"))
                .build();

        List<DeviceResponse> devices = ExchangeHelper.with(restTemplate)
                .getDevices(path);

        assertThat(devices.size(), greaterThan(0));
    }

    @Test
    public void should_GetMultipleFilteredDeviceListCorrect() throws URISyntaxException {
        final String path = PathBuilder.newBuilder(port)
                .addApiExtension(API_EXT_LIST_DEVICE)
                .addQueryParam(Pair.of("brand", "Samsung"))
                .addQueryParam(Pair.of("os", "Android"))
                .build();

        List<DeviceResponse> devices = ExchangeHelper.with(restTemplate)
                .getDevices(path);

        assertThat(devices.size(), greaterThan(0));
    }

    @Test
    public void should_FilteringRecentlyAddedDeviceCorrect() throws URISyntaxException {
        final String putPath = PathBuilder.newBuilder(port)
                .addApiExtension(API_EXT_ADD_DEVICE)
                .build();

        DeviceRequest deviceRequest = newDeviceRequest("get");

        ExchangeHelper.with(restTemplate)
                .putDevice(putPath, deviceRequest);

        final String listPath = PathBuilder.newBuilder(port)
                .addApiExtension(API_EXT_LIST_DEVICE)
                .addQueryParam(Pair.of("brand", deviceRequest.getBrand()))
                .addQueryParam(Pair.of("model", deviceRequest.getModel()))
                .addQueryParam(Pair.of("osVersion", deviceRequest.getOsVersion()))
                .build();

        List<DeviceResponse> devices = ExchangeHelper.with(restTemplate)
                .getDevices(listPath);

        assertThat(devices.size(), equalTo(1));
    }
}