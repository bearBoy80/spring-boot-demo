package com.github.bearboy.spring.actuator.controller;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserDetailControllerTest {
    @Test
    void helloTest(@Autowired MockMvc mvc) throws Exception {
        mvc.perform(get("/hello")).andExpect(status().is3xxRedirection());
    }

    @Test
    void userInfoTest(@Autowired MockMvc mvc) throws Exception {
        mvc.perform(get("/userInfo")).andExpect(status().is3xxRedirection());
    }

    @Test
    @WithMockUser("user")
    void userInfoWithMockUser(@Autowired MockMvc mvc) throws Exception {
        mvc.perform(get("/userInfo")).andExpect(status().isOk());
    }

    @Test
    @WithMockUser("user")
    void helloWithMockUser(@Autowired MockMvc mvc) throws Exception {
        mvc.perform(get("/hello")).andExpect(status().isOk()).andExpect(content().string("hello Word Spring Security"));
    }

    @Test
    @WithMockUser("user")
    void actuatorInfoWithMockUser(@Autowired MockMvc mvc) throws Exception {
        mvc.perform(get("/actuator/info")).andExpect(status().isOk());
    }
}
