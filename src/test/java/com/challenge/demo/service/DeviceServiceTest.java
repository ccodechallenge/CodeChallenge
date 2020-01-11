package com.challenge.demo.service;

import com.challenge.demo.repository.DeviceRepository;
import com.challenge.demo.repository.entity.Device;
import com.challenge.demo.service.entity.DeviceRequest;
import com.challenge.demo.service.entity.DeviceResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by nurisezgin on 11.01.2020.
 */
@RunWith(MockitoJUnitRunner.class)
public class DeviceServiceTest {

    @Mock
    private DeviceRepository mockRepository;

    @InjectMocks
    private DeviceService service = new DeviceServiceImp();

    @Test
    public void should_PutDeviceOperationResultCorrect() {
        Device mockDevice = mock(Device.class);
        when(mockDevice.getId()).thenReturn(1L);
        when(mockRepository.save(any())).thenReturn(mockDevice);

        DeviceResponse response = service.putDevice(new DeviceRequest());

        assertThat(response.getId(), greaterThan(0L));
    }
}