package com.github.bearboy.task;


import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.Schedules;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Configuration(proxyBeanMethods = false)
public class CustomerTask {
    @Scheduled(fixedRate = 1, timeUnit = TimeUnit.SECONDS)
    @Async
    public void doCustomerTask() {
        System.out.println(Thread.currentThread().getName() + " do customer task");
    }

    @Schedules(value = {@Scheduled(cron = "*/10 * * * * MON-FRI")})
    @Async
    public void doCustomerTask1() throws InterruptedException {
        System.out.println(new Date() + "do customer task1 start");
        TimeUnit.SECONDS.sleep(11);
        System.out.println(Thread.currentThread().getName() + "do customer task1");
        System.out.println(new Date() + "do customer task1 end");
    }
}
