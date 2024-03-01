package util;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SendEmail {

    public static void sendEmail(String email,
                                 String confirmCode) {
        Properties properties = new Properties();
        properties.put("mail.smtp.host",
                "smtp.gmail.com");
        properties.put("mail.smtp.port",
                "587");
        properties.put("mail.smtp.auth",
                "true");
        properties.put("mail.smtp.starttls.enable",
                "true");
        Session session = Session.getInstance(properties,
                new Authenticator() {
                    protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("ngohoang8320@gmail.com",
                                "uyuiukvlaautpqqn");
                    }
                });
        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress("ngohoang8320@gmail.com"));
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        try {
            message.setRecipient(Message.RecipientType.TO,
                    new InternetAddress(email));
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        try {
            message.setSubject("Confirm Code");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        try {
            message.setText(confirmCode);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

        try {
            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
