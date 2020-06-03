package com.github.bearboy.spring.common.filter;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.bearboy.spring.common.exception.JwtException;
import com.github.bearboy.spring.common.jwt.BearerTokenResolver;
import com.github.bearboy.spring.common.response.Result;
import com.github.bearboy.spring.common.utils.NimbusJwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.util.MimeTypeUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author bearBoy80
 * token 拦截
 */
public class JwtTokenFilter extends OncePerRequestFilter {

    @Autowired
    BearerTokenResolver bearerTokenResolver;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String token = bearerTokenResolver.resolve(request);
            if (StringUtils.hasText(token)) {
                String username = NimbusJwtUtils.vaildToken(token);
                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    //token 验证成功后，生成认证信息
                    UserDetails details = userDetailsService.loadUserByUsername(username);
                    if (details != null) {
                        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken
                                (details, null, details.getAuthorities());
                        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    }
                } else {
                    response.setContentType(MimeTypeUtils.APPLICATION_JSON_VALUE);
                    response.getOutputStream().write(objectMapper.writeValueAsBytes(Result.fail("token 无效")));
                    return;
                }
            }
        } catch (JwtException ex) {
            response.setContentType(MimeTypeUtils.APPLICATION_JSON_VALUE);
            response.getOutputStream().write(objectMapper.writeValueAsBytes(Result.fail(ex.getMessage())));
            return;
        }
        filterChain.doFilter(request, response);
    }

}
