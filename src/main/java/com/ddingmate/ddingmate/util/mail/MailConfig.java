package com.ddingmate.ddingmate.util.mail;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfig {

    private static final String MAIL_ENCODING = "UTF-8";
    private static final String PROPS_PROTOCOL_SET = "mail.transport.protocol";
    private static final String PROPS_PROTOCOL = "smtp";
    private static final String PROPS_AUTH_SET = "mail.smtp.auth";
    private static final String PROPS_ENABLE_SET = "mail.smtp.starttls.enable";
    private static final String PROPS_DEBUG_SET = "mail.debug";
    private static final String PROPS_DEBUG = "true";


    @Value("${spring.mail.host}")
    public String host;

    @Value("${spring.mail.port}")
    public int port;

    @Value("${spring.mail.username}")
    public String sendEmail;

    @Value("${spring.mail.password}")
    public String sendPassword;

    @Value("${spring.mail.properties.mail.smtp.auth}")
    public boolean auth;

    @Value("${spring.mail.properties.mail.smtp.starttls.enable}")
    public boolean starttlsEnable;

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(host);
        mailSender.setPort(port);
        mailSender.setUsername(sendEmail);
        mailSender.setPassword(sendPassword);
        mailSender.setDefaultEncoding(MAIL_ENCODING);
        mailSender.setJavaMailProperties(getMailProperties());

        Properties props = mailSender.getJavaMailProperties();

        props.put(PROPS_PROTOCOL_SET, PROPS_PROTOCOL);
        props.put(PROPS_AUTH_SET, auth);
        props.put(PROPS_ENABLE_SET, starttlsEnable);
        props.put(PROPS_DEBUG_SET, PROPS_DEBUG);

        return mailSender;
    }

    private Properties getMailProperties() {
        Properties properties = new Properties();
        properties.put(PROPS_AUTH_SET, auth);
        properties.put(PROPS_ENABLE_SET, starttlsEnable);

        return properties;
    }

}
