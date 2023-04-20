package cloud.app.clients.notification;

public record NotificationRequest(
        Integer toUserId,
        String toUserName,
        String message
) {
}