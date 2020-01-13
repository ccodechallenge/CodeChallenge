package com.challenge.demo.repository.entity;

import com.challenge.demo.repository.validator.OSConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * Created by nurisezgin on 11.01.2020.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TBL_DEVICE", uniqueConstraints = {@UniqueConstraint(columnNames = {"BRAND", "MODEL", "OS_VERSION"})})
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @NotEmpty(message = "Brand value must not be empty.")
    @Size(min = 1, max = 20, message = "The length of brand value can be between 1-20")
    @Column(name = "BRAND", length = 20)
    private String brand;

    @NotEmpty(message = "Model value must not be empty.")
    @Size(min = 1, max = 20, message = "The length of model value can be between 1-20")
    @Column(name = "MODEL", length = 20)
    private String model;

    @OSConstraint(message = "Os value can be iOS or Android.")
    @Column(name = "OS", length = 7)
    private String os;

    @NotEmpty(message = "Os Version value must not be empty.")
    @Size(min = 1, max = 20, message = "The length of brand value can be between 1-20")
    @Column(name = "OS_VERSION", length = 20)
    private String osVersion;

}
