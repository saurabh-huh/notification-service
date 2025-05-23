package com.fresco.notification_service.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fresco.notification_service.model.NotificationRequest;
import com.fresco.notification_service.service.EmailService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationConsumer {

    public final EmailService emailService;

    @KafkaListener(topics = "notification-topic", groupId = "notification-group")
    public void consume(String message) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        NotificationRequest request = mapper.readValue(message, NotificationRequest.class);

        emailService.sendEmail(request);
    }
}
