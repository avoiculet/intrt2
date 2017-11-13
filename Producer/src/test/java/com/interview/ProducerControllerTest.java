package com.interview;

import com.interview.producer.Producer;
import org.junit.Test;
import static org.mockito.Mockito.*;
/**
 * Created by avoiculet on 12/11/2017.
 */
public class ProducerControllerTest {
    @Test
    public void testProduceMethodStartsProducer() {
        ProducerController controller = new ProducerController();
        Producer producer = mock(Producer.class);
        controller.setProducer(producer);
        controller.produce();
        verify(producer).start();
    }
}
