package com.bankino.energymanagement.service;

import com.bankino.energymanagement.dto.UserConsumptionDTO;
import com.bankino.energymanagement.entity.UserConsumption;

import java.util.List;

/**
 * Service interface for managing user consumptions.
 */
public interface UserConsumptionService {

    /**
     * Saves a new user consumption based on the provided UserConsumptionDTO.
     *
     * @param userConsumptionDTO The UserConsumptionDTO containing the user consumption data to be saved.
     * @return The created UserConsumption entity.
     */
    UserConsumption saveUserConsumption(UserConsumptionDTO userConsumptionDTO);

    /**
     * Retrieves all user consumptions.
     *
     * @return List of all user consumptions.
     */
    List<UserConsumption> getAllUserConsumptions();

    /**
     * Retrieves a user consumption by its ID.
     *
     * @param id The ID of the user consumption to retrieve.
     * @return The UserConsumption entity if found, otherwise null.
     */
    UserConsumption getUserConsumptionById(Long id);

}
