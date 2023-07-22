package com.bankino.energycostcalculator.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class EnergyMeterConsumptionDTO {

    private double costPerUnit;

    private double consumption;

    private Date consumptionDate;

    private Long userId;

}
