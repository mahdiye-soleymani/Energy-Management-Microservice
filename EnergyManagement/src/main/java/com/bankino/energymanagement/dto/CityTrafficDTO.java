package com.bankino.energymanagement.dto;

import lombok.Builder;
import lombok.Data;

/**
 * this DTO for representing city traffic information.
 */
@Data
@Builder
public class CityTrafficDTO {
    private String city;
    private double consumption;
}