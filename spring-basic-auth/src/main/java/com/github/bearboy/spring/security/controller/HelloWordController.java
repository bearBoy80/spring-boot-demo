package com.github.bearboy.spring.security.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author bearBoy80
 */
@RestController
public class HelloWordController {
    @RequestMapping("/hello")
    public String hello() {
        return "helloWord";
    }
}
