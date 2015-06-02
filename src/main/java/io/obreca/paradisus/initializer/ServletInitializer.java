package io.obreca.paradisus.initializer;

import io.obreca.paradisus.App;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

/**
 * Created by devjackie83@gmail.com on 2015-06-02.
 */
public class ServletInitializer extends SpringBootServletInitializer {

    //외부 톰캣 연결
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(App.class);
    }
}
