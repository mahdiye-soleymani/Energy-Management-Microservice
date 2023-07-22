package com.bankino.energymanagement.service.factory.impl;

import com.bankino.energymanagement.dto.EnergyMeterDTO;
import com.bankino.energymanagement.entity.EnergyMeter;
import com.bankino.energymanagement.enums.EnergyType;
import com.bankino.energymanagement.service.factory.EnergyMeterFactory;

public class ElectricityEnergyMeterFactory implements EnergyMeterFactory {
    @Override
    public EnergyMeter createEnergyMeter(EnergyMeterDTO energyMeterDTO) {
        EnergyMeter energyMeter = new EnergyMeter();
        energyMeter.setType(EnergyType.ELECTRICITY);
        energyMeter.setCostPerUnit(energyMeterDTO.getCostPerUnit());
        energyMeter.setNeighborhood(energyMeterDTO.getNeighborhood());
        energyMeter.setCity(energyMeterDTO.getCity());
        return energyMeter;
    }
}
