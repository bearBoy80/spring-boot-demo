package com.github.bearboy80.arg;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author bearboy80
 */
@SpringBootApplication
public class SpringBootArgDemo {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        Collections.addAll(list, args);
        list.add("--debug=logfile.txt");
        list.add("--server.port=8081");
        SpringApplication.run(SpringBootArgDemo.class, list.toArray(new String[list.size()]));
    }
}

/**
 * 获取arg 参数并打印
 */
@Component
class ArgsBean {
    public ArgsBean(ApplicationArguments args) {
        for (String key : args.getOptionNames()) {
            System.out.println(args.getOptionValues(key));
        }

    }
}