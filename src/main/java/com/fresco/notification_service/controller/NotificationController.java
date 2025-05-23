package com.fresco.notification_service.controller;

import com.fresco.notification_service.kafka.NotificationProducer;
import com.fresco.notification_service.model.NotificationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/notification")
public class NotificationController {

    public final NotificationProducer producer;

    @PostMapping("/send")
    public ResponseEntity<Object> sendNotification(@RequestBody NotificationRequest request){
        try {
            producer.sendNotification("notification-topic", request);
            return ResponseEntity.ok("Notification sent successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to send notification: " + e.getMessage());
        }
    }
}
