package io.obreca.paradisus.initializer;

import io.obreca.paradisus.config.MvcConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.embedded.ServletContextInitializer;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

/**
 * Created by devjackie83@gmail.com on 2015-06-01.
 */
@Configuration
public class Initializer implements ServletContextInitializer {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private static final String MAPPING_URL = "/";

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(MvcConfig.class);

//        servletContext.addListener(new ContextLoaderListener(ctx));
        Dynamic dispatcher = servletContext.addServlet("dispatcher", new DispatcherServlet(ctx));
        dispatcher.addMapping(MAPPING_URL);
        logger.debug(">> {}", MAPPING_URL);
        dispatcher.setLoadOnStartup(1);
    }
}