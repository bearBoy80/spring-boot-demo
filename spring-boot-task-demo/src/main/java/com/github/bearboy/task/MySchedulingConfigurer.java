package com.github.bearboy.task;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.IntervalTask;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.Date;

/**
 * 通过编程方式创建task
 */
@Configuration(proxyBeanMethods = false)
public class MySchedulingConfigurer implements SchedulingConfigurer {
    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        Runnable runnable = () -> {
            System.out.println(new Date() + Thread.currentThread().getName() + "  configureTasks ");
        };
        taskRegistrar.addFixedDelayTask(new IntervalTask(runnable, 1000 * 11, 0));
    }
}
