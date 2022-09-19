package com.github.bearboy.task;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
public class ProducerRedisTask {
    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    //@Scheduled(fixedRate = 11, timeUnit = TimeUnit.SECONDS)
    public void producerToRedis() {
        System.out.println("start producer to redis");
        for (int i = 0; i < 20; i++) {
            Map<String, String> map = new HashMap<>();
            map.put("name" + i, "bearboy");
            map.put("age" + i, "40");
            redisTemplate.opsForStream().add("producerToRedis", map);
        }
    }
}
