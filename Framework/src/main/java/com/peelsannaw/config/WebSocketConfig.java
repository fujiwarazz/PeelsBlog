package com.peelsannaw.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Configuration
public class WebSocketConfig {
//
@Value("${spring.mail.username}")
private String sendFrom;

    @Resource
    JavaMailSenderImpl javaMailSender;


    @Test
    void contextLoads() {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setSubject("peelsannaw");
        simpleMailMessage.setText("thanks");
        simpleMailMessage.setTo("2736522552@qq.com");
        simpleMailMessage.setFrom(sendFrom);
        javaMailSender.send(simpleMailMessage);
    }

    @Test
    void test() throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        //组装
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true,"UTF-8");
        mimeMessageHelper.setSubject("peelsannaw,Hello");
        mimeMessageHelper.setText("<h1 style='color:red;font-weight:bold'> Hello</h1>",true);
        mimeMessageHelper.addAttachment("1.jpg",new File("C:\\Users\\27365\\Pictures\\aquaa.jpg"));
        mimeMessageHelper.setTo("2736522552@qq.com");
        mimeMessageHelper.setFrom(sendFrom);
        javaMailSender.send(mimeMessage);
    }
}
