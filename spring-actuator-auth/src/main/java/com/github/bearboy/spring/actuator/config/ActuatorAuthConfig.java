package com.github.bearboy.spring.actuator.config;

import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author bearBoy80
 * Actuator Endpoint 增加httpBasic 认证，只有用户角色为ADMIN的才能访问
 * 保证优先高于SecurityFormAuthConfig
 */
@Configuration
@Order(10)
public class ActuatorAuthConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.requestMatcher(EndpointRequest.toAnyEndpoint()).authorizeRequests((requests) ->
                requests.anyRequest().hasAnyRole("ADMIN", "USER"));
        http.httpBasic();
    }
}
