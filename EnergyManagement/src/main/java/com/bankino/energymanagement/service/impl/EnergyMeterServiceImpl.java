package com.bankino.energymanagement.service.impl;

import com.bankino.energymanagement.config.MessageByLocaleComponent;
import com.bankino.energymanagement.dto.EnergyMeterConsumptionDTO;
import com.bankino.energymanagement.dto.EnergyMeterDTO;
import com.bankino.energymanagement.entity.EnergyMeter;
import com.bankino.energymanagement.enums.EnergyType;
import com.bankino.energymanagement.exception.ResourceNotFoundException;
import com.bankino.energymanagement.repository.EnergyMeterRepository;
import com.bankino.energymanagement.repository.UserConsumptionRepository;
import com.bankino.energymanagement.service.EnergyMeterService;
import com.bankino.energymanagement.service.factory.EnergyMeterFactory;
import com.bankino.energymanagement.service.factory.impl.ElectricityEnergyMeterFactory;
import com.bankino.energymanagement.service.factory.impl.GasEnergyMeterFactory;
import com.bankino.energymanagement.service.factory.impl.WaterEnergyMeterFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EnergyMeterServiceImpl implements EnergyMeterService {

    private final EnergyMeterRepository energyMeterRepository;
    private final UserConsumptionRepository userConsumptionRepository;
    private final MessageByLocaleComponent message;

    @Autowired
    public EnergyMeterServiceImpl(EnergyMeterRepository energyMeterRepository, UserConsumptionRepository userConsumptionRepository, MessageByLocaleComponent message) {
        this.energyMeterRepository = energyMeterRepository;
        this.userConsumptionRepository = userConsumptionRepository;
        this.message = message;
    }

    @Override
    @Transactional
    public EnergyMeter saveEnergyMeter(EnergyMeterDTO energyMeterDTO) {
        EnergyMeterFactory factory = getFactory(energyMeterDTO.getType());
        EnergyMeter energyMeter = factory.createEnergyMeter(energyMeterDTO);
        return energyMeterRepository.save(energyMeter);
    }

    private EnergyMeterFactory getFactory(EnergyType type) {
        return switch (type) {
            case WATER -> new WaterEnergyMeterFactory();
            case ELECTRICITY -> new ElectricityEnergyMeterFactory();
            case GAS -> new GasEnergyMeterFactory();
            default -> throw new IllegalArgumentException(message.getMessage("invalid.energy.type"));
        };
    }

    @Override
    public List<EnergyMeter> getAllEnergyMeters() {
        return energyMeterRepository.findAll();
    }

    @Override
    public EnergyMeter getEnergyMeterById(Long id) {
        return energyMeterRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(message.getMessage("energy.meter.not.found")));
    }

    @Override
    @Transactional
    public void deleteEnergyMeter(Long id) {
        EnergyMeter existingEnergyMeter = energyMeterRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(message.getMessage("energy.meter.not.found", new Object[]{id})));
        energyMeterRepository.delete(existingEnergyMeter);
    }

    @Override
    @Transactional
    public EnergyMeter updateEnergyMeter(Long energyMeterId, EnergyMeterDTO energyMeterDTO) {
        EnergyMeter existingEnergyMeter = energyMeterRepository.findById(energyMeterId)
                .orElseThrow(() -> new ResourceNotFoundException(message.getMessage("energy.meter.not.found", new Object[]{energyMeterId})));
        existingEnergyMeter.setType(energyMeterDTO.getType());
        existingEnergyMeter.setCostPerUnit(energyMeterDTO.getCostPerUnit());
        existingEnergyMeter.setNeighborhood(energyMeterDTO.getNeighborhood());
        existingEnergyMeter.setCity(energyMeterDTO.getCity());
        return energyMeterRepository.save(existingEnergyMeter);
    }

    @Override
    public EnergyMeterConsumptionDTO getEnergyMeterConsumptionByMaxConsumptionDate() {
        return userConsumptionRepository.findEnergyMeterConsumptionDTOByMaxConsumptionDate();
    }
}
