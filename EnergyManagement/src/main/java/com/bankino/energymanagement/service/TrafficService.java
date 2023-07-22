package com.bankino.energymanagement.service;

import com.bankino.energymanagement.dto.CityTrafficDTO;
import com.bankino.energymanagement.dto.TrafficDTO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service interface for managing traffic data.
 */
@Service
public interface TrafficService {

    /**
     * Get traffic data for each neighborhood in the given city based on consumption.
     *
     * @param city The name of the city for which traffic data is requested.
     * @return A list of traffic data for each neighborhood in the city.
     */
    List<TrafficDTO> getTrafficByCity(String city);

    /**
     * Get the total traffic data for the given city based on consumption of all neighborhoods.
     *
     * @param cityName The name of the city for which total traffic data is requested.
     * @return The total traffic data for the given city.
     */
    CityTrafficDTO getTrafficByCityName(String cityName);


}
