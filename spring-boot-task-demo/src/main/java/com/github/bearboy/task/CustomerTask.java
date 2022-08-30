package com.github.bearboy.task;


import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.concurrent.TimeUnit;

@Configuration(proxyBeanMethods = false)
public class CustomerTask {
    @Scheduled(fixedRate = 1, timeUnit = TimeUnit.SECONDS)
    public void doCustomerTask() {
        System.out.println("do customer task");
    }
}
