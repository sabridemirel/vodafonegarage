package com.sd.forvodafone.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="slot")
public class Slot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="status")
    private int status;
}
