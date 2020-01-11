package com.challenge.demo.configuration;

import com.challenge.demo.Constants;
import com.challenge.demo.repository.entity.Device;
import com.challenge.demo.service.entity.DeviceResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * Created by nurisezgin on 11.01.2020.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BatchConfigurationTest {

    private static final Logger LOG = LoggerFactory.getLogger(BatchConfigurationTest.class);

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void should_BullInsertCompletedSuccessfully() throws IOException {
        int expectedDeviceCount = getExpectedDeviceCount();

        String path = "http://localhost:" + port + "/devices" + "?" + "size=" + Integer.MAX_VALUE;
        ResponseEntity<Object> response = restTemplate.exchange(path,
                HttpMethod.GET, null, Object.class);

        List<DeviceResponse> devices = ((List<DeviceResponse>) response.getBody());

        assertThat(expectedDeviceCount, equalTo(devices.size()));
    }

    private int getExpectedDeviceCount() throws IOException {
        String devicesJson = readContent();

        ObjectMapper mapper = new ObjectMapper();
        List<Device> devices = mapper.readValue(devicesJson, new TypeReference<List<Device>>() {});

        return devices.size();
    }

    private String readContent() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(Constants.FILE_DEVICES).getFile());
        return new String(Files.readAllBytes(file.toPath()));
    }
}