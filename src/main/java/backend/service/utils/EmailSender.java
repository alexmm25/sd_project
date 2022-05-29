package backend.service.utils;

import backend.model.Product;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSender {

    private String emailAddress = "sda3accnew458@gmail.com";
    private String hostAddress = "smtp.gmail.com";

    public void sendEmail(List<Product> foods, String address, String extraDetails) {
        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", hostAddress);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new javax.mail.PasswordAuthentication(emailAddress, "sda3accnew4580459673");
            }
        });
        session.setDebug(true);
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(emailAddress));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailAddress));
            message.setSubject("New order");
            StringBuilder text = new StringBuilder("Ordered items: ");
            text.append("\nPrice: ").append(appendFoodsToMessage(foods, text));
            text.append("\nAddress: ").append(address);
            text.append("\nExtra details: ").append(extraDetails);
            message.setText(text.toString());
            System.out.println("sending...");
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }

    private Integer appendFoodsToMessage(List<Product> foods, StringBuilder text) {
        AtomicInteger price = new AtomicInteger(0);
        foods.forEach(f -> {
            text.append("\n          ").append(f.getName());
            price.set(price.get() + f.getPrice());
        });
        return price.get();
    }
}