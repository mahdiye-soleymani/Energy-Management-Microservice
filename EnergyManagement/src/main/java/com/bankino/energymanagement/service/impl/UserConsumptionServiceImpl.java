package com.bankino.energymanagement.service.impl;

import com.bankino.energymanagement.dto.UserConsumptionDTO;
import com.bankino.energymanagement.dto.UserDTO;
import com.bankino.energymanagement.entity.EnergyMeter;
import com.bankino.energymanagement.entity.UserConsumption;
import com.bankino.energymanagement.repository.UserConsumptionRepository;
import com.bankino.energymanagement.service.EnergyMeterService;
import com.bankino.energymanagement.service.UserConsumptionService;
import com.bankino.energymanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.InvalidParameterException;
import java.util.Date;
import java.util.List;

@Service
public class UserConsumptionServiceImpl implements UserConsumptionService {

    private final UserConsumptionRepository userConsumptionRepository;
    private final UserService userService;
    private final EnergyMeterService energyMeterService;
    private final KafkaTemplate<String, Double> kafkaTemplate;

    @Autowired
    public UserConsumptionServiceImpl(UserConsumptionRepository userConsumptionRepository,
                                      UserService userService,
                                      EnergyMeterService energyMeterService, KafkaTemplate<String, Double> kafkaTemplate) {
        this.userConsumptionRepository = userConsumptionRepository;
        this.userService = userService;
        this.energyMeterService = energyMeterService;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    @Transactional
    public UserConsumption saveUserConsumption(UserConsumptionDTO userConsumptionDTO) {

        UserDTO userDTO = userService.getUserById(userConsumptionDTO.getUserId());
        EnergyMeter energyMeter = energyMeterService.getEnergyMeterById(userConsumptionDTO.getEnergyMeterId());
        if (userDTO == null || energyMeter == null) {
            throw new InvalidParameterException("Invalid user ID or energy meter ID.");
        }
        UserConsumption userConsumption = new UserConsumption();
        userConsumption.setUser(userDTO.toUserEntity());
        userConsumption.setEnergyMeter(energyMeter);
        userConsumption.setConsumptionDate(new Date());
        userConsumption.setConsumption(userConsumptionDTO.getConsumption());
        kafkaTemplate.send("user_consumption_topic", userConsumptionDTO.getConsumption());
        return userConsumptionRepository.save(userConsumption);
    }

    @Override
    public List<UserConsumption> getAllUserConsumptions() {
        return userConsumptionRepository.findAll();
    }

    @Override
    public UserConsumption getUserConsumptionById(Long id) {
        return userConsumptionRepository.findById(id).orElse(null);
    }


}
