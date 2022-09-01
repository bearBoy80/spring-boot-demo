package com.github.bearboy.starter;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
@ConditionalOnProperty(prefix = "my.properties", name = "enable")
@EnableConfigurationProperties(value = MyProperties.class)
public class MyCustomAutoConfiguration {

    @Bean
    public MyCustomBean myCustomBean(MyProperties properties) {
        System.out.println("myCustomBean init");
        System.out.println(properties);
        return new MyCustomBean("bearboy80");
    }
}
