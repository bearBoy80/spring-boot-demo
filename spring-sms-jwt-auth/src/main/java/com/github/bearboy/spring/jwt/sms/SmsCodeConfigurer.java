/*
 * Copyright (c) 2020-2035, bearBoy80 All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * Neither the name of the pig4cloud.com developer nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 *  Author: bearBoy80 (574535393@qq.com)
 *
 *
 */

package com.github.bearboy.spring.jwt.sms;


import com.github.bearboy.spring.common.filter.ValidateCodeFilter;
import com.github.bearboy.spring.common.utils.Constant;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.HttpSecurityBuilder;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.Assert;

import java.util.Arrays;

/**
 * @author bearBoy80
 */
public class SmsCodeConfigurer<H extends HttpSecurityBuilder<H>> extends
        AbstractHttpConfigurer<SmsCodeConfigurer<H>, H> {

    private AuthenticationFailureHandler failureHandler;

    private SmsCodeAuthenticationFilter smsCodeAuthenticationFilter;

    private SmsCodeAuthenticationProvider smsCodeAuthenticationProvider;

    private SavedRequestAwareAuthenticationSuccessHandler defaultSuccessHandler = new SavedRequestAwareAuthenticationSuccessHandler();
    private AuthenticationSuccessHandler successHandler = this.defaultSuccessHandler;

    private RequestMatcher requiresAuthenticationRequestMatcher =
            new AntPathRequestMatcher(Constant.SMS_PROCESS_URL, "POST");

    @Override
    public void init(H builder) throws Exception {
        super.init(builder);
    }

    @Override
    public void configure(H builder) throws Exception {
        smsCodeAuthenticationFilter.setAuthenticationSuccessHandler(successHandler);
        if (failureHandler != null) {
            smsCodeAuthenticationFilter.setAuthenticationFailureHandler(failureHandler);
        }
        smsCodeAuthenticationFilter.setAuthenticationManager(builder
                .getSharedObject(AuthenticationManager.class));

        ValidateCodeFilter codeFilter = new ValidateCodeFilter();
        codeFilter.setUrls(Arrays.asList(Constant.SMS_PROCESS_URL));
        smsCodeAuthenticationFilter.setRequiresAuthenticationRequestMatcher(requiresAuthenticationRequestMatcher);
        builder.authenticationProvider(smsCodeAuthenticationProvider).
                addFilterBefore(smsCodeAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(codeFilter, SmsCodeAuthenticationFilter.class);
    }

    public SmsCodeConfigurer(UserDetailsService userDetailsService) {
        this.smsCodeAuthenticationFilter = new SmsCodeAuthenticationFilter();
        this.smsCodeAuthenticationProvider = new SmsCodeAuthenticationProvider();
        smsCodeAuthenticationProvider.setUserDetailsService(userDetailsService);
    }

    public final SmsCodeConfigurer<H> failureHandler(
            AuthenticationFailureHandler authenticationFailureHandler) {
        this.failureHandler = authenticationFailureHandler;
        return this;
    }

    public final SmsCodeConfigurer<H> successHandler(AuthenticationSuccessHandler successHandler) {
        this.successHandler = successHandler;
        return this;
    }

    public final void setRequiresAuthenticationRequestMatcher(
            RequestMatcher requestMatcher) {
        Assert.notNull(requestMatcher, "requestMatcher cannot be null");
        this.requiresAuthenticationRequestMatcher = requestMatcher;
    }
}
