package com.geekbrains.connectors;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;
import org.springframework.stereotype.Component;

@Component
public class SmtpConnector {

    public void sendEmail() {
//        try {
//            MultiPartEmail email = new MultiPartEmail();
//            email.setHostName("172.16.5.5");
//            email.setSmtpPort(5555);
//            email.setFrom("az@email.ru");
//            email.setSubject("Test");
//            email.setMsg("Test message");
//            email.send();
//        } catch (EmailException e) {
//            System.out.println(e.getLocalizedMessage());
//        }
    }
}
