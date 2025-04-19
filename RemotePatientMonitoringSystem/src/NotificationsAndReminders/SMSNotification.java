package NotificationsAndReminders;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import resources.ConfigLoader;

public class SMSNotification implements Notifiable {

    public static final String ACCOUNT_SID = ConfigLoader.get("accountSid");
    public static final String AUTH_TOKEN = ConfigLoader.get("authToken");
    public static final String FROM_NUMBER = ConfigLoader.get("fromNumber");

    static {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    }


    @Override
    public void sendNotification(ContactInfo contact, String subject, String message) {
        try {
            sendBulkSMS(contact.getPhoneNumber(), message);
        }catch (Exception e) {
            System.out.println("Failed to send SMS to " + contact.getPhoneNumber());
            e.printStackTrace();
        }
    }

    public static void sendBulkSMS(String recipient, String messageBody) {
        Message message = Message.creator(
                new PhoneNumber(recipient),
                new PhoneNumber(FROM_NUMBER),
                messageBody
        ).create();

        System.out.println("Message sent to " + recipient + " | SID: " + message.getSid());
    }
}
