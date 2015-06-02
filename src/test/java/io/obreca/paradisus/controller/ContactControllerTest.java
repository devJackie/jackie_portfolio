package io.obreca.paradisus.controller;

import io.obreca.paradisus.App;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.handler;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Administrator on 2015-06-01.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
@WebAppConfiguration
@Transactional
public class ContactControllerTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void 이메일_보내기_MOCKITO_테스트() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/contact/send-mail")
                .header("json;charset=UTF-8", "application")
                .accept("*/*")
                .param("email","devjackie83@gmail.com");

        MvcResult result = this.mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(handler().handlerType(ContactController.class))
                .andExpect(handler().methodName("sendMail"))
                .andDo(print())
                .andReturn();

        this.mockMvc.perform(MockMvcRequestBuilders.get("/wrong"))
                .andExpect(status().isNotFound())
                .andReturn();
    }
}
