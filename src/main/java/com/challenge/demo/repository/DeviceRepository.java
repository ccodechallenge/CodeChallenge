package com.challenge.demo.repository;

import com.challenge.demo.repository.entity.Device;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by nurisezgin on 11.01.2020.
 */
@Repository
public interface DeviceRepository extends CrudRepository<Device, Long>, JpaSpecificationExecutor<Device> {

    @Query("SELECT c FROM Device c")
    Page<Device> findAll(Pageable pageable);

}
