package com.interview.producer;

import com.interview.model.RTPhoneNumber;
import com.interview.util.Constants;
import com.interview.writer.PhoneNumberWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by avoiculet on 12/11/2017.
 */
@Component
public class SingleThreadProducer implements Producer {
    Logger LOGGER = LoggerFactory.getLogger(SingleThreadProducer.class);
    private PhoneNumberGenerator phoneNumberGenerator;
    private PhoneNumberWriter phoneNumberWriter;
    private AtomicBoolean isStarted = new AtomicBoolean(false);


    ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

    @Override
    public synchronized void start() {

        if (isStarted.get()) {
            return;
        }

        isStarted.set(true);

        LOGGER.info("Started producer with " + Constants.PRODUCER_DELAY + " delay; " + Constants.PRODUCER_PERIOD + " period; TimeUnit = Seconds" );

        executor.scheduleAtFixedRate(
                () -> {
                    try {
                        RTPhoneNumber phoneNumber = phoneNumberGenerator.generate();

                        phoneNumberWriter.write(phoneNumber);
                    } catch(Exception e) {
                        //ignore as we want to keep on writing
                    }
                }, Constants.PRODUCER_DELAY, Constants.PRODUCER_PERIOD, TimeUnit.SECONDS);
    }

    public void setExecutor(ScheduledExecutorService executor) {
        this.executor = executor;
    }

    @Autowired
    public void setPhoneNumberGenerator(PhoneNumberGenerator phoneNumberGenerator) {
        this.phoneNumberGenerator = phoneNumberGenerator;
    }

    @Autowired
    public void setPhoneNumberWriter(PhoneNumberWriter phoneNumberWriter) {
        this.phoneNumberWriter = phoneNumberWriter;
    }



}
