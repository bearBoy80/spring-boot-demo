package com.github.bearboy.spring.form.controller;


import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author bearBoy
 * 获取用户认证信息
 */
@RestController
public class UserDetailController {
    @RequestMapping("/userInfo")
    public User userInfo(@AuthenticationPrincipal User user) {
        return user;
    }
}
