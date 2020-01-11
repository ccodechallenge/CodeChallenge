package com.challenge.demo.controller;

import com.challenge.demo.service.entity.DeviceResponse;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

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
    public void should_PutDeviceResultCorrect() {
        DeviceResponse response = restTemplate.getForObject("http://localhost:" + port + "/", DeviceResponse.class);
        assertThat(response.getId(), greaterThan(0L));
    }

}