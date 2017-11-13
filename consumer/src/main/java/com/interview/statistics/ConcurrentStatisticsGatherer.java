package com.interview.statistics;

import com.interview.model.RTPhoneNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by avoiculet on 13/11/2017.
 */
@Component
public class ConcurrentStatisticsGatherer implements StatisticsGatherer{

    Logger LOGGER = LoggerFactory.getLogger(ConcurrentStatisticsGatherer.class);

    private AtomicInteger total = new AtomicInteger(0);
    ConcurrentHashMap<Integer, LinkedBlockingDeque<RTPhoneNumber>> phoneNumbers = new ConcurrentHashMap<>();

    //TODO: implement limit on the queue ? Requirement needs clarifying
    @Override
    public void submit(RTPhoneNumber phoneNumber) {

        if (phoneNumber == null) {
            return;
        }

        int totalSoFar = total.incrementAndGet();

        LOGGER.info("Total items received: " + totalSoFar);
        
        LinkedBlockingDeque<RTPhoneNumber> dequeue = new LinkedBlockingDeque<RTPhoneNumber>();
        LinkedBlockingDeque<RTPhoneNumber> dequeuFromMap = phoneNumbers.putIfAbsent(phoneNumber.getCountryCode(), dequeue);
        dequeue = dequeuFromMap == null ? dequeue : dequeuFromMap;
        dequeue.add(phoneNumber);
    }

    @Override
    public int getTotal() {
        return total.get();
    }

    //Will return copy of the list to make sure nobody may tamper with it.
    //Will not always show the latest view of the map
    @Override
    public Map<Integer, List<RTPhoneNumber>> getPhoneNumbersGroupedByCountryCode() {

        HashMap<Integer, List<RTPhoneNumber>> map = new HashMap<>();

        phoneNumbers.forEach(
                (k,v)->{
                    map.put(k, copyToList(v));
                }
        );
        return map;
    }

    private List<RTPhoneNumber> copyToList(BlockingDeque<RTPhoneNumber> dequeue) {
        List<RTPhoneNumber> copyList = new ArrayList<>();
        dequeue.stream().forEach(phoneNumber->copyList.add(phoneNumber));
        return copyList;
    }
}
