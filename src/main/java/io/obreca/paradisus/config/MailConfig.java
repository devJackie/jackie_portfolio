package io.obreca.paradisus.config;

import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import javax.validation.constraints.NotNull;
import java.util.Properties;

/**
 * Created by devjackie83@gmail.com on 2015-06-02.
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "mail", locations = {"classpath:/application.yml"})
@ToString
public class MailConfig {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String MAIL_DEBUG = "mail.debug";
    private static final String MAIL_SMTP_STARTTLS_REQUIRED = "mail.smtp.starttls.required";
    private static final String MAIL_SMTP_AUTH = "mail.smtp.auth";
    private static final String MAIL_SMTP_STARTTLS_ENABLE = "mail.smtp.starttls.enable";

    @Data
    public static class Smtp {
        private boolean auth;
        private boolean startTlsRequired;
        private boolean startTlsEnable;
    }

    @NotBlank
    private String host;
    private String protocol;
    private int port;
    private String username;
    private String password;
    private String defaultEncoding;
    @NotNull
    private Smtp smtp;

    @Bean
    public JavaMailSender mailSender() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost(getHost());
        javaMailSender.setPort(getPort());
        javaMailSender.setProtocol(getProtocol());
        javaMailSender.setUsername(getUsername());
        javaMailSender.setPassword(getPassword());
        javaMailSender.setDefaultEncoding(getDefaultEncoding());
        Properties properties = javaMailSender.getJavaMailProperties();
        properties.put(MAIL_SMTP_STARTTLS_REQUIRED, getSmtp().isStartTlsRequired());
        properties.put(MAIL_SMTP_STARTTLS_ENABLE, getSmtp().isStartTlsEnable());
        properties.put(MAIL_SMTP_AUTH, getSmtp().isAuth());
        properties.put(MAIL_DEBUG, true);
        javaMailSender.setJavaMailProperties(properties);

        logger.debug("javaMailSender : {}", javaMailSender.toString());

        return javaMailSender;
    }
}
