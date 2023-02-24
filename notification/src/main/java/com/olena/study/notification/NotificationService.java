package com.olena.study.notification;

import com.olena.study.clients.notification.NotificationRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class NotificationService {

    private NotificationRepository notificationRepository;

    public void sendNotification(NotificationRequest notificationRequest) {
        notificationRepository.save(Notification.builder()
                .toCustomersId(notificationRequest.toCustomerId())
                .toCustomerEmail(notificationRequest.toCustomerName())
                .sender("Olena")
                .message(notificationRequest.message())
                .sentAt(LocalDateTime.now())
                .build());
    }
}
