package com.example.udpm14sellcomputerpartsbackend.service.impl;

import com.example.udpm14sellcomputerpartsbackend.service.MailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import java.util.Map;

@Service
public class MailServiceImpl implements MailService {

    private final JavaMailSender javaMailSender;
    private final SpringTemplateEngine springTemplateEngine;

    @Value("vietndph17667@fpt.edu.vn")
    private String render;

    public MailServiceImpl(JavaMailSender javaMailSender, SpringTemplateEngine springTemplateEngine){
        this.javaMailSender = javaMailSender;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    public void sendMail(Map<String, Object> props, String email, String template, String subject) throws MessagingException {
        Context context = new Context();
        context.setVariables(props);

        String html = springTemplateEngine.process(template,context);

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,true,"utf-8");

        helper.setFrom(render);
        helper.setTo(email);
        helper.setSubject(subject);
        helper.setText(html,true);

        javaMailSender.send(message);

    }



}
