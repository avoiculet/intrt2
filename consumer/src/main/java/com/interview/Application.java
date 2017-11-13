package com.interview;

import com.interview.reader.PhoneNumberReceiver;
import com.interview.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class Application {

    Logger LOGGER = LoggerFactory.getLogger(Application.class);

    public static void main( String[] args ) {
        SpringApplication.run(Application.class, args);
    }

    @Value("${rmq_queue_name}")
    private String queueName;

    @Value("${rmq_exchange_name}")
    private String exchangeName;

    @Value("${rmq_host}")
    private String hostName;

    @Value("${rmq_username}")
    private String rmqUserName;

    @Value("${rmq_password}")
    private String rmqPassword;

    @Bean
    Queue queue() {
        LOGGER.info("Using queue={}",queueName);
        return new Queue(queueName,false);
    }

    @Bean
    TopicExchange exchange() {
        LOGGER.info("Using exchane={}", exchangeName);
        return new TopicExchange(exchangeName);
    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(queueName);
    }

    @Bean
    ConnectionFactory connectionFactory() {
        LOGGER.info("Using hostName={}", hostName);
        CachingConnectionFactory factory = new CachingConnectionFactory(hostName);
        factory.setUsername(rmqUserName);
        factory.setPassword(rmqPassword);
        return factory;
    }

    @Bean
    MessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
                                             MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(queueName);
        MessageConverter converter = jackson2JsonMessageConverter();
        container.setMessageListener(listenerAdapter);
        listenerAdapter.setMessageConverter(converter);
        return container;
    }

    @Bean
    MessageListenerAdapter listenerAdapter(PhoneNumberReceiver reader) {
        return new MessageListenerAdapter(reader, "receiveMessage");
    }

}
