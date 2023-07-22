package com.bankino.energymanagement.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * Data Transfer Object (DTO) for representing User Consumption data when creating or updating user consumptions.
 */
@Getter
@Setter
public class UserConsumptionDTO {

    @NotNull(message = "User ID cannot be null")
    private Long userId;

    @NotNull(message = "Energy Meter ID cannot be null")
    private Long energyMeterId;

    @NotNull(message = "Consumption value cannot be null")
    private double consumption;
}
