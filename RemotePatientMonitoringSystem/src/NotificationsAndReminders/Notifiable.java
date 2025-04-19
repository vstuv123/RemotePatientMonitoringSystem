package NotificationsAndReminders;

public interface Notifiable {
    void sendNotification(ContactInfo contact, String subject, String htmlMessageContent);
}
