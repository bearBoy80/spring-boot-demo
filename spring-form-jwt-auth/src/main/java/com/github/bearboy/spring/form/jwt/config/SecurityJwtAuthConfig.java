

package com.github.bearboy.spring.form.jwt.config;

import com.github.bearboy.spring.common.filter.JwtTokenFilter;
import com.github.bearboy.spring.common.handler.SuccessJwtHandler;
import com.github.bearboy.spring.common.jwt.BearerTokenResolver;
import com.github.bearboy.spring.common.jwt.DefaultBearerTokenResolver;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.ui.DefaultLoginPageGeneratingFilter;

import javax.servlet.http.HttpServletRequest;


/**
 * @author bearBoy80
 * 定义简单的form 表单登录，
 * 默认登录页面为spring security自带页面
 * @see DefaultLoginPageGeneratingFilter#generateLoginPageHtml(HttpServletRequest, boolean, boolean)
 */
@Configuration
public class SecurityJwtAuthConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.   //关闭crsf
                csrf().disable()
                //拦截所有请求
                .authorizeRequests().anyRequest().authenticated().and()
                //设置表单登录
                .formLogin()
                // .loginPage() 自定义登录页面
                //.failureForwardUrl() 认证失败跳转页面url
                //.successForwardUrl() 认证成功跳转页面url
                //.failureHandler() 认证失败拦截器处理，需要自己实现一个AuthenticationFailureHandler,编写自己的业务逻辑处理，
                // 比如登录失败3次，等几分钟在登录，记录登录失败日志等
                //.successHandler() 认证成功拦截器处理，需要自己实现一个AuthenticationSuccessHandler
                // 可以在这里写业务逻辑处理，比如登录成功记录日志、
                //设置登录点
                .successHandler(successHandler())
                .loginProcessingUrl("/myLogin").and()
                //无状态
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(jwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public AuthenticationSuccessHandler successHandler() {
        return new SuccessJwtHandler();
    }

    @Bean
    @ConditionalOnMissingBean(BearerTokenResolver.class)
    public BearerTokenResolver bearerTokenResolver() {
        return new DefaultBearerTokenResolver();
    }

    @Bean
    public JwtTokenFilter jwtTokenFilter() {
        return new JwtTokenFilter();
    }
}
