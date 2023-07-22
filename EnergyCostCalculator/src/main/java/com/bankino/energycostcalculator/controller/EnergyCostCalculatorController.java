package com.bankino.energycostcalculator.controller;

import com.bankino.energycostcalculator.service.EnergyCostCalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/energy-cost-calculator")
public class EnergyCostCalculatorController {

    private final EnergyCostCalculatorService energyCostCalculatorService;

    @Autowired
    public EnergyCostCalculatorController(EnergyCostCalculatorService energyCostCalculatorService) {
        this.energyCostCalculatorService = energyCostCalculatorService;
    }

    @GetMapping("/total-cost")
    public ResponseEntity<Double> calculateTotalCost(
            @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date startDate,
            @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date endDate,
            @RequestParam("userId") Long userId) {

        double totalCost = energyCostCalculatorService.getTotalCostByConsumptionDatesAndUserId(startDate, endDate, userId);
        return ResponseEntity.ok(totalCost);
    }
}
