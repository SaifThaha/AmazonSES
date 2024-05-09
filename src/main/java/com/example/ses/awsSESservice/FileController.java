package com.example.ses.awsSESservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping("/sendEmail")
    public String sendMessage(@RequestParam String fromEmail,
                              @RequestParam String toEmail,
                              @RequestParam String subject,
                              @RequestParam String body){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        simpleMailMessage.setFrom(fromEmail);
        simpleMailMessage.setTo(toEmail);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(body);

        fileService.sendMessage(simpleMailMessage);
        return "Email sent!";
    }
}
