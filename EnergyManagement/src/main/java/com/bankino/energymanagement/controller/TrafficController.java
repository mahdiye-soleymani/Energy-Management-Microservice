package com.bankino.energymanagement.controller;

import com.bankino.energymanagement.dto.CityTrafficDTO;
import com.bankino.energymanagement.dto.TrafficDTO;
import com.bankino.energymanagement.service.TrafficService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/traffic")
public class TrafficController {

    private final TrafficService trafficService;

    @Autowired
    public TrafficController(TrafficService trafficService) {
        this.trafficService = trafficService;
    }

    @GetMapping("/by-city")
    @Operation(summary = "Get traffic data by city", description = "Retrieve a list of traffic data for each neighborhood in the given city based on consumption.")
    public List<TrafficDTO> getTrafficByCity(@RequestParam String city) {
        return trafficService.getTrafficByCity(city);
    }

    @GetMapping("/by-city-name")
    @Operation(summary = "Get traffic data by city name", description = "Retrieve the total traffic data for the given city based on consumption of all neighborhoods.")
    public CityTrafficDTO getTrafficByCityName(@RequestParam String cityName) {
        return trafficService.getTrafficByCityName(cityName);
    }
}
