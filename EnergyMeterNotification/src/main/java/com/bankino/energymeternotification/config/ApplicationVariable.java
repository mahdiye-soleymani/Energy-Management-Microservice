package com.bankino.energymeternotification.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "variable")
@Data
public class ApplicationVariable {
    private double highConsumption;
    private double moderateConsumption;
    private double lowConsumption;
    private double excessiveConsumption;

}