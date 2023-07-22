package com.bankino.energymanagement.service.impl;

import com.bankino.energymanagement.dto.CityTrafficDTO;
import com.bankino.energymanagement.dto.TrafficDTO;
import com.bankino.energymanagement.repository.UserConsumptionRepository;
import com.bankino.energymanagement.service.TrafficService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrafficServiceImpl implements TrafficService {

    private final UserConsumptionRepository userConsumptionRepository;


    @Autowired
    public TrafficServiceImpl(UserConsumptionRepository userConsumptionRepository) {
        this.userConsumptionRepository = userConsumptionRepository;

    }

    @Override
    public List<TrafficDTO> getTrafficByCity(String city) {
        List<Object[]> neighborhoodConsumptionList = userConsumptionRepository.findConsumptionByNeighborhoodAndCity(city);

        return neighborhoodConsumptionList.stream()
                .map(result -> {
                    int neighborhood = (int) result[0];
                    double consumption = (double) result[1];
                    return new TrafficDTO(neighborhood, consumption);
                })
                .collect(Collectors.toList());
    }

    @Override
    public CityTrafficDTO getTrafficByCityName(String cityName) {
        List<Double> neighborhoodConsumptions = userConsumptionRepository.findConsumptionByCity(cityName);
        double totalConsumption = neighborhoodConsumptions.stream()
                .mapToDouble(Double::doubleValue)
                .sum();

        return CityTrafficDTO.builder()
                .city(cityName)
                .consumption(totalConsumption)
                .build();
    }
}
