package com.bankino.energycostcalculator.repository;

import com.bankino.energycostcalculator.entity.EnergyCost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface EnergyCostRepository extends JpaRepository<EnergyCost, Long> {

    List<EnergyCost> findByConsumptionDateBetweenAndUserId(Date startDate, Date endDate, Long userId);

}