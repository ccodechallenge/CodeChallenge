package com.challenge.demo.repository;

import com.challenge.demo.repository.entity.Device;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by nurisezgin on 11.01.2020.
 */
@Repository
public interface DeviceRepository extends CrudRepository<Device, Long> { }
