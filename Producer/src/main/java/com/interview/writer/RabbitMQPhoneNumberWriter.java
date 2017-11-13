package com.interview.writer;

import com.interview.model.PhoneNumberMessage;
import com.interview.model.PhoneNumberMessageImpl;
import com.interview.model.RTPhoneNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by avoiculet on 12/11/2017.
 */
@Component
public class RabbitMQPhoneNumberWriter implements PhoneNumberWriter {

    Logger LOGGER = LoggerFactory.getLogger(RabbitMQPhoneNumberWriter.class);

    @Value("${rmq_queue_name}")
    private String queueName;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void setRabbitTemplate(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void write(RTPhoneNumber phoneNumber) {
        PhoneNumberMessage message = new PhoneNumberMessageImpl(phoneNumber.getTelephoneNumber());
        LOGGER.info("Sending phoneNumber={}", message);
        rabbitTemplate.convertAndSend(queueName,  message);
    }
}
