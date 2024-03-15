package com.bearboy.tomcat.customizer;

import org.apache.catalina.Context;
import org.springframework.boot.web.embedded.tomcat.TomcatContextCustomizer;
import org.springframework.context.annotation.Configuration;

/**
 * 通过customizer开启tomcat cross
 */
@Configuration(proxyBeanMethods = false)
public class MyTomcatContextCustomizer implements TomcatContextCustomizer {
    @Override
    public void customize(Context context) {
        System.out.println("开始tomcat context customizer.....");
        context.setCrossContext(true);
    }
}
