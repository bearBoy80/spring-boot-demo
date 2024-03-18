package com.github.bearboy.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication
public class SpringBootKafkaDemo implements CommandLineRunner {
    @Autowired
    private KafkaTemplate<String, User> kafkaTemplate;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootKafkaDemo.class,args);
    }

    @Override
    public void run(String... args) throws Exception {
        kafkaTemplate.send("quickstart-events","bearboy",new User(18,"bearboy"));
    }
}
