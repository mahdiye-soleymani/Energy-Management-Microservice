package com.bankino.energymanagement.repository;

import com.bankino.energymanagement.dto.EnergyMeterConsumptionDTO;
import com.bankino.energymanagement.entity.UserConsumption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserConsumptionRepository extends JpaRepository<UserConsumption, Long> {

    @Query("SELECT new com.bankino.energymanagement.dto.EnergyMeterConsumptionDTO(uc.energyMeter.costPerUnit, uc.consumption, uc.consumptionDate, uc.user.id) FROM UserConsumption uc WHERE uc.consumptionDate = (SELECT MAX(uc2.consumptionDate) FROM UserConsumption uc2)")
    EnergyMeterConsumptionDTO findEnergyMeterConsumptionDTOByMaxConsumptionDate();

    @Query("SELECT uc.user.neighborhood, SUM(uc.consumption) FROM UserConsumption uc WHERE uc.user.city = :city GROUP BY uc.user.neighborhood")
    List<Object[]> findConsumptionByNeighborhoodAndCity(@Param("city") String city);


    @Query("SELECT u.consumption FROM UserConsumption u JOIN u.user user WHERE user.city =:cityName")
    List<Double> findConsumptionByCity(String cityName);


}

