package me.helmify;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.BlockingQueue;

@Component
public class KafkaConsumer {

    static final BlockingQueue<String> messages = new java.util.concurrent.LinkedBlockingQueue<>();

    @KafkaListener(topics = KafkaTopicConfig.KAKFA_TOPIC, groupId = "test")
    public void listenGroupFoo(String message) {
        System.out.println("Received Message in group foo: " + message);
        messages.add(message);
    }

}
