package com.interview;

import com.interview.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class Application
{
    Logger LOGGER = LoggerFactory.getLogger(Application.class);

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

    public static void main( String[] args ) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    Queue queue() {
        return new Queue(queueName,false);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(exchangeName);
    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(queueName);
    }

    @Bean
    RabbitTemplate rabbitTemplate(ConnectionFactory rabbitConnectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(rabbitConnectionFactory);
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        return rabbitTemplate;
    }

    @Bean
    ConnectionFactory connectionFactory() {
        CachingConnectionFactory factory = new CachingConnectionFactory(hostName);
        LOGGER.info("Started producer to use rmg hostname: " + hostName);
        factory.setUsername(rmqUserName);
        factory.setPassword(rmqPassword);
        return factory;
    }
}
