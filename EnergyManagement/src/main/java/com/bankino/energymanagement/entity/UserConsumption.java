package com.bankino.energymanagement.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "user_consumptions")
@Getter
@Setter
@RequiredArgsConstructor
public class UserConsumption {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_consumption_sequence")
    @SequenceGenerator(name = "user_consumption_sequence", sequenceName = "user_consumption_sequence", allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "energy_meter_id", nullable = false)
    private EnergyMeter energyMeter;

    @Column(nullable = false)
    private Date consumptionDate;

    @Column(nullable = false)
    private double consumption;
}
