package com.bearboy.tomcat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * tomcat相关配置定制化
 */
@SpringBootApplication
@RequestMapping
public class TomcatCustomizerDemo {
    public static void main(String[] args) {
        SpringApplication.run(TomcatCustomizerDemo.class,args);
    }

    @GetMapping("/sayhello")
    @ResponseBody
    public String sayHello(){
        return "echo hello";
    }
}


