package com.github.bearboy.json;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author bearboy80
 */
@SpringBootApplication
public class SpringBootJsonDemo {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootJsonDemo.class, args);
    }
}

@RestController
class IndexController {
    @RequestMapping(value = "/index")
    public User index(String name, int age) {
        return new User(name, age);
    }
}

