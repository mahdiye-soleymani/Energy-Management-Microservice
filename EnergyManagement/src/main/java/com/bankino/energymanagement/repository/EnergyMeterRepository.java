package com.bankino.energymanagement.repository;

import com.bankino.energymanagement.entity.EnergyMeter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnergyMeterRepository extends JpaRepository<EnergyMeter, Long> {
}
