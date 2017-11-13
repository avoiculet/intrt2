package com.interview;

import com.interview.model.RTPhoneNumber;

import java.util.List;
import java.util.Map;

/**
 * Created by avoiculet on 13/11/2017.
 */
public class StatisticResponse {
    private final int total;
    private final Map<Integer, List<RTPhoneNumber>> grouping;

    public StatisticResponse(int total, Map<Integer, List<RTPhoneNumber>> grouping) {
        this.total = total;
        this.grouping = grouping;
    }

    public int getTotal() {
        return total;
    }

    public Map<Integer, List<RTPhoneNumber>> getGrouping() {
        return grouping;
    }
}
