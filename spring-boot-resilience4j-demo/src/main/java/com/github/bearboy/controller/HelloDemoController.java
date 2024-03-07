package com.github.bearboy.controller;


import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class HelloDemoController {
    @Autowired
    private CircuitBreakerRegistry circuitBreakers;

    /**
     * fallbackMethod: 与CircuitBreaker没有关系，只是包装下函数抛出的异常
     *
     * @param message
     * @return
     * @throws IOException
     */
    @RequestMapping("/sayHello")
    @Bulkhead(name = "backendA")
    @CircuitBreaker(name = "backendA", fallbackMethod = "fallBack")
    public String sayHello(String message) throws IOException {
        if ("fall".equals(message)) {
            throw new IOException("new ioexception");
        }
        return "sayHello" + message;
    }

    /**
     *
     * @param input - 对应调用方输入的参数
     * @param e 捕获的异常
     * @return
     */
    public String fallBack(String input, Exception e) {
        io.github.resilience4j.circuitbreaker.CircuitBreaker circuitBreaker = circuitBreakers.circuitBreaker("backendA");
        System.out.println(circuitBreaker.getState());
        System.out.println(input + "- " + e.getMessage());
        return "fallback";
    }
}
