package com.Almaamouny.NotificationService;

public record EmailRequest(
         String to,
         String subject,
         String text

) {
}
