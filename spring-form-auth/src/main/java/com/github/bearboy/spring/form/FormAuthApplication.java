package com.github.bearboy.spring.form;

import lombok.extern.java.Log;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author bearBoy80
 */
@SpringBootApplication
@Log
public class FormAuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(FormAuthApplication.class, args);
        log.info("系统启动成功");
    }
}
