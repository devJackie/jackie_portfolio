package io.obreca.paradisus.controller;

import io.obreca.paradisus.domain.Contact;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by devjackie83@gmail.com on 2015-06-01.
 */
@RestController
@RequestMapping(value = "/contact")
public class ContactController {

    @RequestMapping(value = "/send-mail", method = RequestMethod.POST)
    public @ResponseBody Object sendMail(@ModelAttribute @Valid Contact contact) {

        return "OK";
    }
}
