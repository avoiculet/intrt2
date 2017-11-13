package com.interview.producer;

import com.interview.model.RTPhoneNumber;
import com.interview.writer.PhoneNumberWriter;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.*;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
/**
 * Created by avoiculet on 12/11/2017.
 */
public class SingleThreadProducerTest {
    private PhoneNumberGenerator generator;
    private ScheduledExecutorService executor;
    private SingleThreadProducer producer;
    private PhoneNumberWriter writer;

    @Before
    public void setUp() {
        this.generator = mock(PhoneNumberGenerator.class);
        this.executor = mock(ScheduledExecutorService.class);
        this.writer = mock(PhoneNumberWriter.class);
        this.producer = new SingleThreadProducer();
        producer.setExecutor(executor);
        producer.setPhoneNumberGenerator(generator);
        producer.setPhoneNumberWriter(writer);
    }

    @Test
    public void testProducerSchedulesJobCorrectly() {
        when(executor.scheduleAtFixedRate(any(Runnable.class), anyLong(), anyLong(), any(TimeUnit.class)))
                .thenAnswer(invocationOnMock -> {
                    Object[] args = invocationOnMock.getArguments();
                    Runnable task = (Runnable) args[0];
                    long delay = (long) args[1];
                    long period = (long) args[2];
                    TimeUnit unit = (TimeUnit) args[3];
                    assertTrue(delay == 1);
                    assertTrue(period == 5);
                    assertTrue(unit.equals(TimeUnit.SECONDS));
                    task.run();
                    return mock(ScheduledFuture.class);
                });
        producer.start();
        verify(generator).generate();
        verify(writer).write(any(RTPhoneNumber.class));
        verify(executor).scheduleAtFixedRate(any(Runnable.class), anyLong(), anyLong(), any(TimeUnit.class));
    }

    @Test
    public void testExceptionOnWriteIsCaughtByTheRunnable() {
        when(executor.scheduleAtFixedRate(any(Runnable.class), anyLong(), anyLong(), any(TimeUnit.class)))
                .thenAnswer(invocationOnMock -> {
                    Object[] args = invocationOnMock.getArguments();
                    Runnable task = (Runnable) args[0];
                    long delay = (long) args[1];
                    long period = (long) args[2];
                    TimeUnit unit = (TimeUnit) args[3];
                    assertTrue(delay == 1);
                    assertTrue(period == 5);
                    assertTrue(unit.equals(TimeUnit.SECONDS));
                    try {
                        task.run();
                    } catch(Exception e) {
                        fail();
                    }
                    return mock(ScheduledFuture.class);
                });
        doThrow(new RuntimeException("Should not be seen")).when(writer).write(any());
        producer.start();
        verify(writer).write(any());
    }
}
