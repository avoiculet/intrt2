package com.interview.reader;

import com.interview.model.IdPhoneNumber;
import com.interview.model.PhoneNumberMessageImpl;
import com.interview.statistics.StatisticsGatherer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * Created by avoiculet on 13/11/2017.
 */
@Component
public class RabbitMQPhoneNumberReceiver implements PhoneNumberReceiver {
    Logger LOGGER = LoggerFactory.getLogger(PhoneNumberReceiver.class);
    private StatisticsGatherer statisticsGatherer;

    @Autowired
    public void setStatisticsGatherer(StatisticsGatherer statisticsGatherer) {
        this.statisticsGatherer = statisticsGatherer;
    }

    @Override
    public void receiveMessage(PhoneNumberMessageImpl message) {
       LOGGER.info("Received: " + message.toString());
       statisticsGatherer.submit(new IdPhoneNumber(message.getId(), message.getTelephoneNumber()));
    }
}
