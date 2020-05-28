package com.github.bearboy.spring.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


/**
 * @author bearBoy80
 * @see WebSecurityConfigurerAdapter
 * 增加对/hello,/ 请求进行httpBasic 认证
 * 默认用户名和密码见  application.properties  spring.security.user.name spring.security.user.password
 */
@Configuration
public class HttpBasicSecurityconfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.requestMatchers().antMatchers("/hello", "/").and().
                authorizeRequests().anyRequest().authenticated().and().httpBasic();
    }
}
