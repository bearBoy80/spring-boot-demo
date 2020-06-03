

package com.github.bearboy.spring.common.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.bearboy.spring.common.response.Result;
import com.github.bearboy.spring.common.response.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.util.MimeTypeUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author bearBoy80
 */
@Slf4j
public class AuthEntryPointJwt implements AuthenticationEntryPoint {

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        log.error("Unauthorized error: {}", authException.getMessage());
        response.setContentType(MimeTypeUtils.APPLICATION_JSON_VALUE);
        response.getOutputStream().write(objectMapper.writeValueAsBytes(Result.fail("Unauthorized").setCode(ResultCode.UNAUTHORIZED.code())));
    }

}