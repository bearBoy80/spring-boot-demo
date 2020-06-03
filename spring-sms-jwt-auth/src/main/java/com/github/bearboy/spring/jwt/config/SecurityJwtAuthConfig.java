

package com.github.bearboy.spring.jwt.config;

import com.github.bearboy.spring.common.authentication.AuthEntryPointJwt;
import com.github.bearboy.spring.common.filter.JwtTokenFilter;
import com.github.bearboy.spring.common.handler.SuccessJwtHandler;
import com.github.bearboy.spring.common.jwt.BearerTokenResolver;
import com.github.bearboy.spring.common.jwt.DefaultBearerTokenResolver;
import com.github.bearboy.spring.jwt.sms.SmsCodeConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.AuthenticationEntryPoint;
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

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        SmsCodeConfigurer smsCodeConfigurer = new SmsCodeConfigurer(userDetailsService);
        smsCodeConfigurer.successHandler(successHandler());
        http.apply(smsCodeConfigurer);
        http.   //关闭crsf
                csrf().disable()
                //sms/code 发送短信验证码 通过
                .authorizeRequests().antMatchers( "/sms/code").permitAll()
                .and()
                //拦截所有请求
                .authorizeRequests().anyRequest().authenticated().and()
                .exceptionHandling().authenticationEntryPoint(authEntryPointJwt()).and()
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

    @Bean
    public AuthenticationEntryPoint authEntryPointJwt() {
        return new AuthEntryPointJwt();
    }

}
