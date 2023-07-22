package com.bankino.energymanagement.entity;

import com.bankino.energymanagement.enums.EnergyType;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;

@Entity
@Table(name = "energy_meters")
@Getter
@Setter
public class EnergyMeter {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "energy_meter_sequence")
    @SequenceGenerator(name = "energy_meter_sequence", sequenceName = "energy_meter_sequence", allocationSize = 1)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EnergyType type;

    @Column(name = "cost_per_unit", nullable = false)
    private double costPerUnit;

    @Column(name = "neighborhood", nullable = false, length = 50)
    private int neighborhood;

    @Column(name = "city", nullable = false)
    private String city;
}
