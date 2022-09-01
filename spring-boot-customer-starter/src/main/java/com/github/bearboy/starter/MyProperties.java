package com.github.bearboy.starter;

import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties(prefix = "my.properties")
public class MyProperties {
    private String id;
    private String name;
}
