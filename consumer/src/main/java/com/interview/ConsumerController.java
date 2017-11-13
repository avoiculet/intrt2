package com.interview;

import com.interview.model.RTPhoneNumber;
import com.interview.statistics.StatisticsGatherer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Created by avoiculet on 13/11/2017.
 */
@RestController
public class ConsumerController {

    private StatisticsGatherer statisticsGatherer;

    @Autowired
    public void setStatisticsGatherer(StatisticsGatherer statisticsGatherer) {
        this.statisticsGatherer = statisticsGatherer;
    }

    @RequestMapping("/")
    public StatisticResponse start() {
        int total = statisticsGatherer.getTotal();

        Map<Integer, List<RTPhoneNumber>> grouping = statisticsGatherer.getPhoneNumbersGroupedByCountryCode();

        return new StatisticResponse(total, grouping);
    }


}
