package com.interview.reader;

import com.interview.model.PhoneNumberMessageImpl;
import com.interview.statistics.StatisticsGatherer;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

/**
 * Created by avoiculet on 13/11/2017.
 */
public class RabbitMQPhoneNumberReaderTest {
    private RabbitMQPhoneNumberReceiver reader;
    private StatisticsGatherer statisticsGatherer;

    @Before
    public void setup() {
        this.reader = new RabbitMQPhoneNumberReceiver();
        this.statisticsGatherer = mock(StatisticsGatherer.class);
        reader.setStatisticsGatherer(statisticsGatherer);

    }

    @Test
    public void testReaderSubmits() {
        reader.receiveMessage(new PhoneNumberMessageImpl("+447506999090"));
        verify(statisticsGatherer).submit(any());

    }
}
