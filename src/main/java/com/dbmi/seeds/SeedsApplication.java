package com.dbmi.seeds;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SeedsApplication {

    private static final Logger myLog = LoggerFactory.getLogger(SeedsApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SeedsApplication.class, args);
    } // MAIN(STRING[])

} // CLASS
