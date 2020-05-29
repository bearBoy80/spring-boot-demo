package com.github.bearboy.spring.actuator;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.core.SpringVersion;

import java.util.HashMap;

/**
 * @author bearBoy80
 */
@SpringBootApplication
public class ActuatorAuthApplication {
    public static void main(String[] args) {
        SpringApplicationBuilder builder = new SpringApplicationBuilder();
        HashMap map = new HashMap(6);
        map.put("springFrame.version", SpringVersion.getVersion());
        builder.properties(map).sources(ActuatorAuthApplication.class).main(ActuatorAuthApplication.class).run(args);
    }
}
