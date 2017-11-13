package com.interview.writer;

import com.interview.model.IdPhoneNumber;
import org.junit.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import static org.mockito.Mockito.*;

/**
 * Created by avoiculet on 13/11/2017.
 */
public class RabbitMQPhoneNumberWriterTest {

    @Test
    public void testWriteSendsViaRabbitTemplate() {
        RabbitTemplate template = mock(RabbitTemplate.class);
        RabbitMQPhoneNumberWriter writer = new RabbitMQPhoneNumberWriter();
        writer.setRabbitTemplate(template);
        writer.write(new IdPhoneNumber("21212121","+441615005096"));
        verify(template).convertAndSend(anyString(), any(Object.class));
    }
}
