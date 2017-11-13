package com.interview;

import com.interview.producer.Producer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Created by avoiculet on 13/11/2017.
 */
@Component
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

    Logger LOGGER = LoggerFactory.getLogger(ApplicationStartup.class);

    private Producer producer;

    @Autowired
    public void setProducer(Producer producer) {
        this.producer = producer;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        producer.start();
    }
}
