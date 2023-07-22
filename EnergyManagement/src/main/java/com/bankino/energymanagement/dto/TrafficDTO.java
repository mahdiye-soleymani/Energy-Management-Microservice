package com.bankino.energymanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TrafficDTO {

    private int neighborhood;

    private double consumption;

}
