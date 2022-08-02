package com.example.kafka.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListeners {
    Logger LOG = LoggerFactory.getLogger(KafkaListeners.class);

    @KafkaListener(topics = "topic1")
    void listener(String data) {
        LOG.info(data);
    }

//    @KafkaListener(
//            topics = "topic1, topic2",
//            groupId = "myGroup")
//    void commonListenerForMultipleTopics(String message) {
//        LOG.info("MultipleTopicListener - {}", message);
//    }
}
