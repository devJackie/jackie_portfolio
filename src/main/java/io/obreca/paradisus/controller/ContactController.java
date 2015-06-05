package io.obreca.paradisus.controller;

import io.obreca.paradisus.domain.Contact;
import io.obreca.paradisus.service.ContactService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

/**
 * Created by devjackie83@gmail.com on 2015-06-01.
 */
@RestController
@RequestMapping(value = "/contact")
public class ContactController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ContactService contactService;

    @RequestMapping(value = "/send-mail", method = RequestMethod.POST)
    public @ResponseBody Object sendMail(@ModelAttribute @Valid Contact contact) {
        logger.debug(">> {} ",contact.toString());

        Map<String,Object> result = contactService.sendMail(contact);
        return result;
    }
}
