package com.cpd.coronapreventiondivision.Handler;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public class EmailHandler {

    private final static String username = "coronapreventiondivision";
    private final static String password = "coronaprevention1";
    private static Session session;

    public EmailHandler(){
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
    }

    public boolean sendConfirmation(String receivee, String verificationCode){
        try {
            String fileName = "src/main/resources/EmailDrafts/confirmation.html";
            Path path = Path.of(fileName);

            String subject = "Welcome to Corona Prevention Division!";
            String htmlContent = Files.readString(path);
            htmlContent = htmlContent.replace("website.com", "https://www.youtube.com/watch?v=dQw4w9WgXcQ");

            return sendEmail(receivee, subject, htmlContent);
        }
        catch(Exception e){
            return false;
        }
    }

    public boolean sendResults(String receivee, String resultCode){
        try {
            String fileName = "src/main/resources/EmailDrafts/results.html";
            Path path = Path.of(fileName);

            String subject = "Your test results are here";
            String htmlContent = Files.readString(path);
            htmlContent = htmlContent.replace("website.com", "https://www.youtube.com/watch?v=dQw4w9WgXcQ");

            return sendEmail(receivee, subject, htmlContent);
        }
        catch(Exception e){
            return false;
        }
    }

    public boolean sendEmail(String addressList, String subject, String content){
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("coronapreventiondivision@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(addressList));
            message.setSubject(subject);
            message.setContent(content, "text/html");

            Transport.send(message);
        }
        catch (MessagingException e) {
            return false;
        }
        return true;
    }
}