package com.bankino.energymanagement.service;

import com.bankino.energymanagement.dto.EnergyMeterConsumptionDTO;
import com.bankino.energymanagement.dto.EnergyMeterDTO;
import com.bankino.energymanagement.entity.EnergyMeter;

import java.util.List;

/**
 * This interface defines the contract for the EnergyMeter service.
 */
public interface EnergyMeterService {

    /**
     * Saves an EnergyMeter entity to the database.
     *
     * @param energyMeterDTO The DTO containing the data to create an EnergyMeter.
     * @return The created EnergyMeter entity.
     */
    EnergyMeter saveEnergyMeter(EnergyMeterDTO energyMeterDTO);


    /**
     * Retrieves all EnergyMeters from the database.
     *
     * @return List of all EnergyMeters.
     */
    List<EnergyMeter> getAllEnergyMeters();


    /**
     * Retrieves an EnergyMeter by its ID from the database.
     *
     * @param id The ID of the EnergyMeter to retrieve.
     * @return The EnergyMeter if found, otherwise null.
     */
    EnergyMeter getEnergyMeterById(Long id);


    /**
     * Deletes an EnergyMeter from the database by its ID.
     *
     * @param id The ID of the EnergyMeter to delete.
     */
    void deleteEnergyMeter(Long id);

    /**
     * Updates an EnergyMeter entity in the database.
     *
     * @param energyMeterId  The ID of the energy meter to update.
     * @param energyMeterDTO The DTO containing the updated data for the EnergyMeter.
     * @return The updated EnergyMeter entity if found, otherwise null.
     */
    EnergyMeter updateEnergyMeter(Long energyMeterId, EnergyMeterDTO energyMeterDTO);


    /**
     * Retrieves Energy Meter consumption data by User Consumption ID.
     *
     * @return EnergyMeterConsumptionDTO containing costPerUnit and consumption data,
     *         or null if User Consumption ID is not found.
     */
    EnergyMeterConsumptionDTO getEnergyMeterConsumptionByMaxConsumptionDate();




}
