package com.example.kafka.listeners;

import com.example.kafka.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class KafkaListeners {
    Logger LOG = LoggerFactory.getLogger(KafkaListeners.class);

    @KafkaListener(topics = "topic1")
    void listener(@Payload String data, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
        LOG.info("Message from topic1 " + data + " partition: " + partition);
    }

    @KafkaListener(
            topics = "topic2",
            groupId = "mwai.group")
    void commonListenerForMultipleTopics(String message) {
        LOG.info("MultipleTopicListener - {}", message);
    }


    @KafkaListener(topics = "user",containerFactory="userKafkaListenerContainerFactory",groupId = "mwai.group")
    void userListener(User user) {
        LOG.info("Message from user topic " + user);
    }
}
