package com.github.bearboy.spring.common.jwt;

import javax.servlet.http.HttpServletRequest;

/**
 * @author bearBoy80
 * token 解析接口
 */
public interface BearerTokenResolver {


    String resolve(HttpServletRequest request);

}