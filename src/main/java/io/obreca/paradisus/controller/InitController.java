package io.obreca.paradisus.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by devjackie83@gmail.com on 2015-04-29.
 */
@Controller
@RequestMapping(value = "/")
public class InitController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "", method=RequestMethod.GET)
    public ModelAndView index() {
        log.debug("{}", "reload test");
        ModelAndView mav = new ModelAndView();
        mav.setViewName("index");
        return mav;
    }

    @RequestMapping(value = "test", method=RequestMethod.GET)
    public ModelAndView test() {
        log.debug("{}", ">>>>>>>>>>>>>> test in");
        ModelAndView mav = new ModelAndView();
        mav.setViewName("test");
        return mav;
    }

    //jsp 테스트 (thymeleaf와 동시 작업 안됨)
    @RequestMapping(value = "hello", method=RequestMethod.GET)
    public ModelAndView hello() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("hello");
        return mav;
    }
}
