package com.github.bearboy;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
    @Autowired
    private StringRedisTemplate redisTemplate;

    @RequestMapping("/index/set")
    public String setValue(String value) {
        redisTemplate.opsForValue().append("index", value);
        return "sucess";
    }

    @RequestMapping("/index/get")
    public String getValue(String key) {
        return redisTemplate.opsForValue().get(key);
    }
}
