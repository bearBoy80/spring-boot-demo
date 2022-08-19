package com.github.bearboy80.cache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class CacheDemo {
    public static void main(String[] args) {
        SpringApplication.run(CacheDemo.class, args);
    }
}

