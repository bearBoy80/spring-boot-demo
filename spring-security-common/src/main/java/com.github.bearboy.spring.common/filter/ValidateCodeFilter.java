
package com.github.bearboy.spring.common.filter;

import com.github.bearboy.spring.common.exception.JwtException;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * @author bearBoy80
 */
public class ValidateCodeFilter extends OncePerRequestFilter {

    /**
     * 存放所有需要校验验证码的url,比如手机验证码
     */
    private List<String> urls = new ArrayList();

    /**
     * 验证请求url与配置的url是否匹配的工具类
     */
    private AntPathMatcher pathMatcher = new AntPathMatcher();


    public void setUrls(List<String> urls) {
        this.urls = urls;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        //判断是否需要拦截请求
        if (matchUrl(request)) {
            String smsCode = request.getParameter("smsCode");
            try {
                if (StringUtils.hasText(smsCode)) {
                    //todo 实现短信验证码逻辑
                    logger.info("验证码校验通过");
                } else {
                    throw new JwtException("短信验证码不能为空");
                }

            } catch (Exception exception) {
                throw new JwtException(exception.getMessage());
            }
        }
        chain.doFilter(request, response);
    }

    private boolean matchUrl(HttpServletRequest request) {
        boolean flag = false;
        for (String url : urls) {
            if (pathMatcher.match(url, request.getRequestURI())) {
                flag = true;
                break;
            }
        }
        return flag;
    }
}
