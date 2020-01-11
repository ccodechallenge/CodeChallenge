package com.challenge.demo.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by nurisezgin on 11.01.2020.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TBL_DEVICE")
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "BRAND", length = 20)
    private String brand;

    @Column(name = "MODEL", length = 20)
    private String model;

    @Column(name = "OS", length = 7)
    private String os;

    @Column(name = "OS_VERSION", length = 20)
    private String osVersion;

}
