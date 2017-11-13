package com.interview;

import com.interview.producer.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by avoiculet on 12/11/2017.
 */
@RestController
public class ProducerController {


    private Producer producer;

    @RequestMapping("/")
    public String start() {
        return "Producing events";
    }

    @RequestMapping("/produce")
    public String produce() {
        producer.start();
        return "Started";
    }

    @Autowired
    public void setProducer(Producer producer) {
        this.producer = producer;
    }
}
