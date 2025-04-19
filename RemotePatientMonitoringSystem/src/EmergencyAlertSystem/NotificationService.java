package EmergencyAlertSystem;

import resources.ConfigLoader;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class NotificationService {

    public void sendEmail(String to, String subject, String htmlContent) {
        String senderEmail = ConfigLoader.get("senderEmail");
        String senderPassword = ConfigLoader.get("senderPassword");

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", ConfigLoader.get("smtpServer"));
        props.put("mail.smtp.port", ConfigLoader.get("smtpPort"));

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(senderEmail, senderPassword);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(to)
            );
            message.setSubject(subject);

            message.setContent(htmlContent, "text/html");

            Transport.send(message);

            System.out.println("Real Email Sent Successfully to " + to + "!");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}

