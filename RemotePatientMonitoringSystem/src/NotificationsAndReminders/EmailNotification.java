package NotificationsAndReminders;

import EmergencyAlertSystem.NotificationService;

public class EmailNotification implements Notifiable {

    NotificationService ns = new NotificationService();

    @Override
    public void sendNotification(ContactInfo contact, String subject, String htmlMessageContent) {
        ns.sendEmail(contact.getEmail(), subject, htmlMessageContent);
    }
}
