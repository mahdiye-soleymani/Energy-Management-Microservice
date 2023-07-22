package com.bankino.energycostcalculator.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "energy_costs")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnergyCost {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "energy_cost_sequence")
    @SequenceGenerator(name = "energy_cost_sequence", sequenceName = "energy_cost_sequence", allocationSize = 1)
    private Long id;

    @Column(nullable = false)
    private double cost;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date consumptionDate;
}
