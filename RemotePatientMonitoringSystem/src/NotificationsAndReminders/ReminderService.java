package NotificationsAndReminders;

import java.util.ArrayList;

public class ReminderService {
    private final ArrayList<Notifiable> notifiers;

    public ReminderService(ArrayList<Notifiable> notifiers) {
        this.notifiers = notifiers;
    }

    public void sendReminder(ContactInfo contact, String subject, String message) {
        for (Notifiable notifier : notifiers) {
            notifier.sendNotification(contact, subject, message);
        }
    }
}

