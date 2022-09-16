package com.github.bearboy;


import com.github.bearboy.bo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @RequestMapping("/index/set")
    public String setValue(String value) {
        stringRedisTemplate.opsForValue().append("index", value);
        redisTemplate.opsForValue().set("user", new User("bearboy80", 35));
        return "sucess";
    }

    @RequestMapping("/index/get")
    public String getValue(String key) {
        return stringRedisTemplate.opsForValue().get(key) + redisTemplate.opsForValue().get("user");
    }
}
