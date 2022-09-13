package com.github.bearboy.task;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskDecorator;
import org.springframework.util.StopWatch;

@Configuration(proxyBeanMethods = false)
public class CustomTaskDecorator implements TaskDecorator {
    @Override
    public Runnable decorate(Runnable runnable) {
        return () -> {
            StopWatch stopWatch = new StopWatch("CustomTaskDecorator");
            stopWatch.start();
            runnable.run();
            stopWatch.stop();
            System.out.println("taskName:" + runnable.getClass().getName() + "---" + stopWatch.getLastTaskTimeMillis() + "毫秒");
        };
    }
}
