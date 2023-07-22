package com.bankino.energycostcalculator.feign;

import com.bankino.energycostcalculator.dto.EnergyMeterConsumptionDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "energy-management-service", url = "http://localhost:8081")
public interface EnergyManagementClient {

    @GetMapping("/energy-meters/cost-per-unit")
    EnergyMeterConsumptionDTO getEnergyMeterConsumptionByMaxConsumptionDate();

}
