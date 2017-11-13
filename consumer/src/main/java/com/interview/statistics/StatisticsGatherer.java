package com.interview.statistics;

import com.interview.model.RTPhoneNumber;

import java.util.List;
import java.util.Map;

/**
 * Created by avoiculet on 13/11/2017.
 */
public interface StatisticsGatherer {
    public void submit(RTPhoneNumber phoneNumber);

    int getTotal();

    Map<Integer, List<RTPhoneNumber>> getPhoneNumbersGroupedByCountryCode();
}
