package com.bankino.energycostcalculator.service;

import java.util.Date;

public interface EnergyCostCalculatorService {

    /**
     * Calculates the total energy cost for a specific user within the given date range.
     *
     * @param startDate The start date of the consumption range.
     * @param endDate The end date of the consumption range.
     * @param userId The ID of the user to calculate the total energy cost for.
     * @return The total energy cost for the given user and date range.
     * @throws RuntimeException if no energy cost data is found for the given user and dates.
     */
    double getTotalCostByConsumptionDatesAndUserId(Date startDate, Date endDate, Long userId);


}
