package com.bankino.energymeternotification.service;

import com.bankino.energymeternotification.config.ApplicationVariable;
import com.bankino.energymeternotification.config.MessageByLocaleComponent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EnergyMeterNotificationListener {

    private final ApplicationVariable appVar;
    private final MessageByLocaleComponent message;

    public EnergyMeterNotificationListener(ApplicationVariable appVar, MessageByLocaleComponent message) {
        this.appVar = appVar;
        this.message = message;
    }

    @KafkaListener(topics = "user_consumption_topic", groupId = "my-consumer-group")
    public void listen(Double consumption) {
        if (consumption > appVar.getHighConsumption() && consumption <= appVar.getExcessiveConsumption()) {
            log.warn(message.getMessage("highConsumptionWarning"));

        } else if (consumption >= appVar.getModerateConsumption() && consumption <= appVar.getLowConsumption()) {
            log.info(message.getMessage("lowConsumptionInfo"));

        } else if (consumption < appVar.getModerateConsumption()) {
            log.warn(message.getMessage("veryLowConsumptionWarning"));

        } else if (consumption > appVar.getExcessiveConsumption()) {
            log.warn(message.getMessage("excessiveConsumptionWarning"));

        } else {
            log.info(message.getMessage("regularSubscriberInfo"));
        }
    }
}
