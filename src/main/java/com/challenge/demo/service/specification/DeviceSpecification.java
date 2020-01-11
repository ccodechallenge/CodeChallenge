package com.challenge.demo.service.specification;

import com.challenge.demo.repository.entity.Device;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class DeviceSpecification implements Specification<Device> {

    private final Map<String, String> attributes;

    private DeviceSpecification(Device device) {
        attributes = new HashMap<>();

        fillOutFromDevice(device);
    }

    public static DeviceSpecification with(Device device) {
        return new DeviceSpecification(device);
    }

    private void fillOutFromDevice(Device device) {
        attributes.put("brand", device.getBrand());
        attributes.put("model", device.getModel());
        attributes.put("os", device.getOs());
        attributes.put("osVersion", device.getOsVersion());
    }

    @Override
    public Predicate toPredicate(Root<Device> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder builder) {
        List<Predicate> predicates = new ArrayList<>();

        attributes.entrySet().stream()
                .filter(this::hasValidValue)
                .map(entry -> builder.and(builder.equal(root.get(entry.getKey()), entry.getValue())))
                .forEach(predicates::add);

        return builder.and(predicates.toArray(new Predicate[predicates.size()]));
    }

    private boolean hasValidValue(Map.Entry<String, String> entry) {
        return entry.getValue() != null && !entry.getValue().trim().isEmpty();
    }
}
