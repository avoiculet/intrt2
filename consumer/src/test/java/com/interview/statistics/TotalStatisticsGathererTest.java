package com.interview.statistics;

import com.interview.model.IdPhoneNumber;
import com.interview.model.RTPhoneNumber;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.junit.Assert.*;
/**
 * Created by avoiculet on 13/11/2017.
 */
public class TotalStatisticsGathererTest {
    private ConcurrentStatisticsGatherer statisticsGatherer;

    @Before
    public void setUp() {
        this.statisticsGatherer = new ConcurrentStatisticsGatherer();
    }

    @Test
    public void testSubmitUpdatesTotalAndGrouping() {
        //Submit should increment a total of the messages received
        //should add the number to the list of country codes.
        RTPhoneNumber phoneNumber = new IdPhoneNumber(UUID.randomUUID().toString(),"+447506907666");
        RTPhoneNumber bulgarianNumber = new IdPhoneNumber(UUID.randomUUID().toString(),"+35949234567");
        RTPhoneNumber ukPhoneNumber2 = new IdPhoneNumber(UUID.randomUUID().toString(),"+447446668989");
        RTPhoneNumber bulgarianNumber2 = new IdPhoneNumber(UUID.randomUUID().toString(),"+35949234467");
        RTPhoneNumber usNumber1  = new IdPhoneNumber(UUID.randomUUID().toString(),"+14085551212");
        RTPhoneNumber usNumber2 = new IdPhoneNumber(UUID.randomUUID().toString(),"+14085551212");

        statisticsGatherer.submit(phoneNumber);
        statisticsGatherer.submit(bulgarianNumber);
        statisticsGatherer.submit(bulgarianNumber2);
        statisticsGatherer.submit(ukPhoneNumber2);
        statisticsGatherer.submit(usNumber1);
        statisticsGatherer.submit(usNumber2);

        assertTrue(statisticsGatherer.getTotal() == 6);
        Map<Integer, List<RTPhoneNumber>> phoneNumbers = statisticsGatherer.getPhoneNumbersGroupedByCountryCode();
        assertTrue(phoneNumbers.size() == 3);
        List<RTPhoneNumber> ukList = phoneNumbers.get(44);
        assertTrue(ukList.size() == 2);
        assertTrue(ukList.contains(phoneNumber));
        assertTrue(ukList.contains(ukPhoneNumber2));

        List<RTPhoneNumber> bulgarianList = phoneNumbers.get(359);
        assertTrue(bulgarianList.size() == 2);
        assertTrue(bulgarianList.contains(bulgarianNumber));
        assertTrue(bulgarianList.contains(bulgarianNumber2));

        List<RTPhoneNumber> usList = phoneNumbers.get(1);
        assertTrue(usList.size() == 2);
        assertTrue(usList.contains(usNumber1));
        assertTrue(usList.contains(usNumber2));
    }

    @Test
    public void testModificationOfListDoesNotModifyInternalView() {
        RTPhoneNumber phoneNumber = new IdPhoneNumber(UUID.randomUUID().toString(),"+447506907666");
        statisticsGatherer.submit(phoneNumber);
        Map<Integer, List<RTPhoneNumber>> phoneNumbers = statisticsGatherer.getPhoneNumbersGroupedByCountryCode();
        List<RTPhoneNumber> ukNumbers = phoneNumbers.get(44);
        assertTrue(ukNumbers.size() == 1);
        ukNumbers.add(new IdPhoneNumber(UUID.randomUUID().toString(),"+441615089999"));
        assertTrue(ukNumbers.size() == 2);
        phoneNumbers.put(22, new LinkedList<>());
        phoneNumbers = statisticsGatherer.getPhoneNumbersGroupedByCountryCode();
        ukNumbers = phoneNumbers.get(44);
        assertTrue(ukNumbers.size() == 1);
        assertTrue(phoneNumbers.get(22) == null);

    }
}
