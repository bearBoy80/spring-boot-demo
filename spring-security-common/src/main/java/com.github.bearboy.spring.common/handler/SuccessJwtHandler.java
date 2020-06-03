package com.github.bearboy.spring.common.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.bearboy.spring.common.response.Result;
import com.github.bearboy.spring.common.utils.NimbusJwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.util.MimeTypeUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @author bearBoy80
 * form 表单登录成功处理器--生成token
 */
public class SuccessJwtHandler implements AuthenticationSuccessHandler {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        User details = (User) authentication.getPrincipal();
        String token = NimbusJwtUtils.buildJWT(details);
        response.setContentType(MimeTypeUtils.APPLICATION_JSON_VALUE);
        response.getOutputStream().write(objectMapper.writeValueAsBytes(Result.data(token)));
    }

}
