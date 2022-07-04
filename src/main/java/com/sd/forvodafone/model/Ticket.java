package com.sd.forvodafone.model;


import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name="ticket")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="vehicletypeId")
    private int vehicleTypeId;

    @Column(name="plate")
    private String plate;

    @Column(name="colour")
    private String colour;

    @Column(name="slots")
    private String slots;

    @Column(name="status")
    private int status;

    @Column(name = "starttime")
    private LocalDateTime starttime;

    @Column(name = "endtime")
    private LocalDateTime endtime;
}
