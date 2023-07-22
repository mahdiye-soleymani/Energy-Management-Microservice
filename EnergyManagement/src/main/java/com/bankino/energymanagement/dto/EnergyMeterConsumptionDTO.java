package com.bankino.energymanagement.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class EnergyMeterConsumptionDTO {

    private double costPerUnit;

    private double consumption;

    private Date consumptionDate;

    private Long userId;
}
