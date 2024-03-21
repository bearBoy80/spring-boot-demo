package com.github.bearboy.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.concurrent.ListenableFuture;

@SpringBootApplication
public class SpringBootKafkaDemo implements CommandLineRunner {
    @Autowired
    private KafkaTemplate<String, User> kafkaTemplate;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootKafkaDemo.class,args);
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        //异步返回结果
        ListenableFuture<SendResult<String, User>> result = kafkaTemplate.send("quickstart-events","bearboy",new User(18,"bearboy"));
        System.out.println(result.get().getProducerRecord().timestamp());
    }
}
