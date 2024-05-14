package com.example.ses.awsSESservice.service;

import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class EmailService {
    @Autowired
    private AmazonSimpleEmailService amazonSimpleEmailService;
    public String from = "saifmohamed1996@gmail.com";
    public String[] to = {"saif@vepay.io"};
    private String templateName = "MyTemplate";
    private String templateData = "{ \"name\":\"Jack\", \"favoriteanimal\": \"Tiger\"}";
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

        SendTemplatedEmailRequest sendTemplatedEmailRequest = new SendTemplatedEmailRequest();
        sendTemplatedEmailRequest.setDestination(destination);
        sendTemplatedEmailRequest.setTemplate(templateName);
        sendTemplatedEmailRequest.setTemplateData(templateData);
        sendTemplatedEmailRequest.setSource(simpleMailMessage.getFrom());

        amazonSimpleEmailService.sendTemplatedEmail(sendTemplatedEmailRequest);
    }
}
