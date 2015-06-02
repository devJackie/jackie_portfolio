package io.obreca.paradisus.service;

import io.obreca.paradisus.App;
import junit.framework.TestCase;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.mail.internet.MimeMessage;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
public class ContactServiceTest extends TestCase {

    @Autowired
    private JavaMailSender mailSender;

    @Test
    public void testSendMail() throws Exception {

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
            messageHelper.setTo("devjackie83@gmail.com");
            messageHelper.setText("메일 테스트");
            messageHelper.setFrom("sypioneerdev@gmail.com");
            messageHelper.setSubject("test");

            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Ignore
    public void 메일_보내기_MOCKITO_테스트() throws Exception {

    }
}