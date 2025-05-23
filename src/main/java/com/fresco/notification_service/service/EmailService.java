package com.fresco.notification_service.service;

import com.fresco.notification_service.model.NotificationRequest;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
@RequiredArgsConstructor
public class EmailService {
    public final JavaMailSender mailSender;


    public void sendEmail(NotificationRequest request) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(request.getTo());
        helper.setSubject(request.getSubject());

        if ("file".equalsIgnoreCase(request.getCategory()) && request.getFileBase64() != null) {
            byte[] fileBytes = Base64.getDecoder().decode(request.getFileBase64());
            helper.setText("Please find the attached file.");
            helper.addAttachment("attachment", new ByteArrayResource(fileBytes));
        } else {
            helper.setText(request.getMessage(), true);
        }
        mailSender.send(message);
    }
}
