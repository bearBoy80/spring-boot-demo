package com.github.bearboy.task;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author bearboy80
 */
@EnableAsync
@SpringBootApplication
public class SpringBootTaskDemo {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootTaskDemo.class, args);
    }
}

@RestController
class Index {
    private ObjectProvider<Index> index;

    public Index(ObjectProvider<Index> index) {
        this.index = index;
    }

    @GetMapping("/index")
    public DeferredResult<String> index() {
        DeferredResult<String> deferredResult = new DeferredResult<String>();
        index.getIfAvailable().setResult(deferredResult);
        System.out.println("拿到结果");
        return deferredResult;
    }

    @GetMapping("/index1")
    @Async
    public CompletableFuture<String> index1() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return CompletableFuture.completedFuture("Result is ready!");
    }

    @GetMapping("/index2")
    public Callable<String> index2() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return () -> "index2";
    }

    @GetMapping("/index3")
    public DeferredResult<String> index3() {
        DeferredResult<String> deferredResult = new DeferredResult<String>();
        setResult(deferredResult);
        System.out.println("index3拿到结果");
        return deferredResult;
    }

    @Async
    public void setResult(DeferredResult<String> deferredResult) {
        try {
            System.out.println("进入异步处理中");
            TimeUnit.SECONDS.sleep(5);
            deferredResult.setResult("hello");
            System.out.println("异步处理完成");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
