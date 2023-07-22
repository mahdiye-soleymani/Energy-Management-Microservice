package com.bankino.energycostcalculator.service.impl;

import com.bankino.energycostcalculator.entity.EnergyCost;
import com.bankino.energycostcalculator.feign.EnergyManagementClient;
import com.bankino.energycostcalculator.repository.EnergyCostRepository;
import com.bankino.energycostcalculator.service.EnergyCostCalculatorService;
import com.bankino.energycostcalculator.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@EnableScheduling
public class EnergyCostCalculatorServiceImpl implements EnergyCostCalculatorService {

    private final EnergyManagementClient energyManagementClient;
    private final EnergyCostRepository energyCostRepository;

    @Autowired
    public EnergyCostCalculatorServiceImpl(EnergyManagementClient energyManagementClient, EnergyCostRepository energyCostRepository) {
        this.energyManagementClient = energyManagementClient;
        this.energyCostRepository = energyCostRepository;
    }

    /**
     * This method is scheduled to run with an initial delay of 2 minutes
     * and then every 30 minutes.
     * It calculates the energy cost based on the latest energy meter consumption data and saves it to the database.
     */
    @Scheduled(initialDelay = 120000, fixedRate = 1800000)
    public void calculateEnergyCost() {
        Optional.ofNullable(energyManagementClient.getEnergyMeterConsumptionByMaxConsumptionDate())
                .ifPresent(energyMeterConsumptionDTO -> {
                    double costPerUnit = energyMeterConsumptionDTO.getCostPerUnit();
                    double consumption = energyMeterConsumptionDTO.getConsumption();
                    Date formattedDate = DateUtils.formatDate(energyMeterConsumptionDTO.getConsumptionDate());

                    EnergyCost energyCost = EnergyCost.builder()
                            .cost(costPerUnit * consumption)
                            .userId(energyMeterConsumptionDTO.getUserId())
                            .consumptionDate(formattedDate)
                            .build();

                    energyCost = energyCostRepository.save(energyCost);
                    System.out.println("Energy cost calculated and saved: " + energyCost.getCost());
                });
    }


    @Override
    public double getTotalCostByConsumptionDatesAndUserId(Date startDate, Date endDate, Long userId) {
        double totalCost = energyCostRepository
                .findByConsumptionDateBetweenAndUserId(startDate, endDate, userId)
                .stream()
                .mapToDouble(EnergyCost::getCost)
                .sum();

        if (totalCost > 0) {
            return totalCost;
        } else {
            throw new RuntimeException("No energy cost data found for the given user and dates.");
        }
    }


}
