package io.obreca.paradisus.service;

import io.obreca.paradisus.domain.Contact;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by devjackie83@gmail.com on 2015-06-01.
 */
public interface ContactService {

    Map<String,Object> sendMail(Contact contact);
}
