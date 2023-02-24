package com.olena.study.clients.notification;

public record NotificationRequest(Integer toCustomerId, String toCustomerName, String message) {
}
