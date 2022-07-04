package com.sd.forvodafone.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="vehicletypes")
public class VehicleTypes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="slotsize")
    private String slotsize;
}
