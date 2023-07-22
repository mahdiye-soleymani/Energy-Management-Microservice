package com.bankino.energymanagement.dto;

import com.bankino.energymanagement.enums.EnergyType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
public class EnergyMeterDTO {

    private Long id;

    @NotBlank(message = "Type cannot be empty")
    private EnergyType type;

    @NotBlank(message = "Cost cannot be empty")
    private double costPerUnit;

    @NotBlank(message = "Neighborhood cannot be empty")
    private int neighborhood;

    @NotBlank(message = "City cannot be empty")
    private String city;
}
