package com.github.bearboy.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

/**
 * 注册接收kafka传递过来的消息
 */
@Component
public class KafkaTopicListenerDemo {
    /**
     * 指定监听kafka topic和分区
     * @param content
     */
    @KafkaListener(topics = "quickstart-events",topicPattern = "0")
    public void processMessage(User content) {
        System.out.println("接收到kafka topic quickstart-events" + content);
    }
}