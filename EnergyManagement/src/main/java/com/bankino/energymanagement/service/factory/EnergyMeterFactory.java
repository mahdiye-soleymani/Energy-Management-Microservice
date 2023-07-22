package com.bankino.energymanagement.service.factory;

import com.bankino.energymanagement.dto.EnergyMeterDTO;
import com.bankino.energymanagement.entity.EnergyMeter;

public interface EnergyMeterFactory {
    EnergyMeter createEnergyMeter(EnergyMeterDTO energyMeterDTO);
}

