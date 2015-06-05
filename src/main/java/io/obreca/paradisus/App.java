package io.obreca.paradisus;

import io.obreca.paradisus.initializer.Initializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * Created by devjackie83@gmail.com on 2015-04-29.
 */
@SpringBootApplication
public class App {

    public static void main(String[] args) {
        SpringApplication.run(new Class[]{
                App.class,
                Initializer.class
        }, args);
    }
}
