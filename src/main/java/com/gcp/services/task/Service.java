package com.gcp.services.task;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication
@PropertySources({
        @PropertySource("classpath:auth0.properties")
})
public class Service {
    private static Logger log = Logger.getLogger(Service.class.getName());

    public static void main(String[] args) {
        log.debug("Starting Tasks List Services");
        SpringApplication.run(Service.class, args);
        log.debug("Tasks List Services Started");
    }
}
