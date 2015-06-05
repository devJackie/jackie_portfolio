package io.obreca.paradisus.service.impl;

import io.obreca.paradisus.config.MailConfig;
import io.obreca.paradisus.domain.Contact;
import io.obreca.paradisus.service.ContactService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by devjackie83@gmail.com on 2015-06-01.
 */
@Service("ContactService")
public class ContactServiceImpl implements ContactService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private MailConfig mailConfig;

    @Override
    public Map<String, Object> sendMail(Contact contact) {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
            messageHelper.setTo("devjackie83@gmail.com");
            messageHelper.setText("name : " + contact.getName() + "\r\nemail : " + contact.getEmail() + "\r\nmessage : " + contact.getMessage());
            messageHelper.setSubject("CONTACT ME");
            messageHelper.setFrom(mailConfig.getUsername());

            javaMailSender.send(message);
            result.put("result", "success");
        } catch (Exception e) {
            result.put("result", "failure");
            e.printStackTrace();
        }
        return result;
    }
}
