package com.fresco.notification_service.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
public class NotificationRequest {
    private String to;
    private String subject;
    private String message;
    private String category; // otp, message, file
    private String fileBase64; // optional for file
}

