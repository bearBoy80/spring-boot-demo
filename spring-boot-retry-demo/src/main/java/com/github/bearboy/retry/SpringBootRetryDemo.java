package com.github.bearboy.retry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.task.TaskSchedulingAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.RetryListener;
import org.springframework.retry.annotation.EnableRetry;

/**
 * @author bearboy80
 * @EnableRetry 开启重试
 */
@SpringBootApplication(exclude = TaskSchedulingAutoConfiguration.class)
@EnableRetry(proxyTargetClass = true)
public class SpringBootRetryDemo {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootRetryDemo.class, args);
    }

    @Bean
    public RetryListener retryListener1() {
        RetryListener retryListener = new RetryListener() {
            @Override
            public <T, E extends Throwable> boolean open(RetryContext context, RetryCallback<T, E> callback) {
                System.out.println("retryListener1 open");
                return true;
            }

            @Override
            public <T, E extends Throwable> void close(RetryContext context, RetryCallback<T, E> callback, Throwable throwable) {
                System.out.println("retryListener1 close");

            }

            @Override
            public <T, E extends Throwable> void onError(RetryContext context, RetryCallback<T, E> callback, Throwable throwable) {
                System.out.println("retryListener1 onError");
            }
        };
        return retryListener;
    }
}

