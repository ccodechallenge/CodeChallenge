package com.challenge.demo.service.mapper;

import com.challenge.demo.repository.entity.Device;
import com.challenge.demo.service.entity.DeviceRequest;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by nurisezgin on 11.01.2020.
 */
public class FromDeviceRequestTest {

    @Test
    public void should_ConversionIsCorrect() {
        DeviceRequest deviceRequest = DeviceRequest.builder()
                .brand("brand")
                .model("model")
                .os("os")
                .osVersion("osVersion")
                .build();

        Device device = new FromDeviceRequest(deviceRequest)
                .toDevice();

        assertThat(device, new DeviceRequestMatcher(deviceRequest));
    }

    @Test(expected = NullPointerException.class)
    public void should_ExceptionThrownWhenGivenDeviceRequestValueIsNull() {
        new FromDeviceRequest(null)
                .toDevice();
    }

    private static class DeviceRequestMatcher extends TypeSafeMatcher<Device> {

        private final DeviceRequest deviceRequest;

        public DeviceRequestMatcher(DeviceRequest deviceRequest) {
            this.deviceRequest = deviceRequest;
        }

        @Override
        protected boolean matchesSafely(Device device) {
            return deviceRequest.getBrand().equals(device.getBrand())
                    && deviceRequest.getModel().equals(device.getModel())
                    && deviceRequest.getOs().equals(device.getOs())
                    && deviceRequest.getOsVersion().equals(device.getOsVersion());
        }

        @Override
        public void describeTo(Description description) {
            description.appendText("Given devicerequest and device are not identical.");
        }
    }


}