package com.github.bearboy.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.stream.MapRecord;
import org.springframework.data.redis.connection.stream.ReadOffset;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ConsumerRedisTask {
    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;
    private int index = 0;

    //@Scheduled(fixedRate = 1, timeUnit = TimeUnit.SECONDS)
    public void consumerToRedis() {
        System.out.println("start consumer from redis");
        ReadOffset readOffset = ReadOffset.from(String.valueOf(index));
        List<MapRecord<Object, Object, Object>> list = redisTemplate.boundStreamOps("producerToRedis").read(readOffset);
        for (MapRecord<Object, Object, Object> map : list) {
            System.out.println(map.getId());
            map.forEach(objectObjectEntry -> System.out.println("" + objectObjectEntry.getKey() + objectObjectEntry.getValue()));
        }
        index++;
    }
}
