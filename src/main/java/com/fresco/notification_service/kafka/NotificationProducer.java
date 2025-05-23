package com.fresco.notification_service.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fresco.notification_service.model.NotificationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationProducer {
    public final KafkaTemplate<String,String> kafkaTemplate;


    public void sendNotification(String topic , NotificationRequest request) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(request);
        kafkaTemplate.send(topic, json);
    }
}
