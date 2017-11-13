import com.interview.ConsumerController;
import com.interview.StatisticResponse;
import com.interview.model.RTPhoneNumber;
import com.interview.statistics.StatisticsGatherer;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
/**
 * Created by avoiculet on 13/11/2017.
 */
public class ConsumerControllerTest {

    private ConsumerController controller;
    private StatisticsGatherer statisticsGatherer;

    @Before
    public void setup() {
        this.controller = new ConsumerController();
        this.statisticsGatherer = mock(StatisticsGatherer.class);
        controller.setStatisticsGatherer(statisticsGatherer);
    }
    @Test
    public void testControllerReturnsTotalAndGrouping() {
        int total = 10;
        Map<Integer, List<RTPhoneNumber>> map = new HashMap<>();
        when(statisticsGatherer.getPhoneNumbersGroupedByCountryCode()).thenReturn(map);
        when(statisticsGatherer.getTotal()).thenReturn(total);
        StatisticResponse response = controller.start();
        assertTrue(response.getTotal() == total);
        assertTrue(response.getGrouping().equals(map));
    }
}
