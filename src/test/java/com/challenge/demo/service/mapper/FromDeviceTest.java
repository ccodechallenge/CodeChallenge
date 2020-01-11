package com.challenge.demo.service.mapper;

import com.challenge.demo.repository.entity.Device;
import com.challenge.demo.service.entity.DeviceResponse;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by nurisezgin on 11.01.2020.
 */
public class FromDeviceTest {

    @Test
    public void should_ConversionIsCorrect() {
        Device device = Device.builder()
                .id(1L)
                .brand("brand")
                .model("model")
                .os("os")
                .osVersion("osVersion")
                .build();

        DeviceResponse actual = new FromDevice(device)
                .toResponse();

        assertThat(actual, new DeviceMatcher(device));
    }

    @Test(expected = NullPointerException.class)
    public void should_ExceptionThrownWhenGivenDeviceValueIsNull() {
        new FromDevice(null)
                .toResponse();
    }

    private static class DeviceMatcher extends TypeSafeMatcher<DeviceResponse> {

        private final Device device;

        public DeviceMatcher(Device device) {
            this.device = device;
        }

        @Override
        protected boolean matchesSafely(DeviceResponse deviceResponse) {
            return device.getId() == deviceResponse.getId()
                    && device.getOsVersion().equals(deviceResponse.getOsVersion())
                    && device.getOs().equals(deviceResponse.getOs())
                    && device.getModel().equals(deviceResponse.getModel())
                    && device.getBrand().equals(deviceResponse.getBrand());
        }

        @Override
        public void describeTo(Description description) {
            description.appendText("Given device and device response are not identical.");
        }
    }


}