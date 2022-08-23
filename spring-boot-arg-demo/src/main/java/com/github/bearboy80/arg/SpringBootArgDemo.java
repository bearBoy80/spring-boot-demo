package com.github.bearboy80.arg;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author bearboy80
 * application.yml 设置的端口9000将会被忽略，实现端口是8081
 */
@SpringBootApplication
public class SpringBootArgDemo {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        Collections.addAll(list, args);
        list.add("--debug=logfile.txt");
        list.add("--server.port=8081");
        list.add("--userName=bearboy80");
        SpringApplication.run(SpringBootArgDemo.class, list.toArray(new String[list.size()]));
    }
}

/**
 * 获取arg 参数并打印
 */
@Component
class ArgsBean {
    public ArgsBean(ApplicationArguments args, @Value("${userName}") String userName) {
        System.out.println("接收到userName：" + userName);
        for (String key : args.getOptionNames()) {
            System.out.println(args.getOptionValues(key));
        }
    }
}