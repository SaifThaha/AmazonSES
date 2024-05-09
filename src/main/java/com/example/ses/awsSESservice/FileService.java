package com.example.ses.awsSESservice;

import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class FileService {
    @Autowired
    private AmazonSimpleEmailService amazonSimpleEmailService;
    public void sendMessage(SimpleMailMessage simpleMailMessage) {
        Destination destination = new Destination();
        destination.setToAddresses(Arrays.asList(simpleMailMessage.getTo()));

        Content content = new Content();
        content.setData(simpleMailMessage.getText());

        Body body = new Body();
        body.setText(content);

        Content subject = new Content();
        subject.setData(simpleMailMessage.getSubject());

        Message message = new Message();
        message.setBody(body);
        message.setSubject(subject);

        SendEmailRequest sendEmailRequest = new SendEmailRequest();
        sendEmailRequest.setDestination(destination);
        sendEmailRequest.setMessage(message);
        sendEmailRequest.setSource(simpleMailMessage.getFrom());

        amazonSimpleEmailService.sendEmail(sendEmailRequest);
    }
}
