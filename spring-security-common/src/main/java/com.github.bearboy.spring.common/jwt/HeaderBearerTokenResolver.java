
package com.github.bearboy.spring.common.jwt;

import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;

/**
 * @author bearBoy80
 * 从header 获取token
 */
public class HeaderBearerTokenResolver implements BearerTokenResolver {

    private String header;

    public HeaderBearerTokenResolver(String header) {
        Assert.hasText(header, "header cannot be empty");
        this.header = header;
    }

    @Override
    public String resolve(HttpServletRequest request) {
        return request.getHeader(this.header);
    }
}