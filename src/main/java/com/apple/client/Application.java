/*
 * Copyright 2019 Apple. All Rights Reserved.
 *
 * This software is the proprietary information of Apple.
 * Use is subject to license terms.
 */
package com.apple.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * Main class for Apple Client Application
 *
 * @author Kiran Kanaparthi
 * @since May 03, 2019
 */
@SpringBootApplication
@EnableAsync
public class Application {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    /**
     * Main entry method for starting the Application.
     * @param args arguments from command line.
     */
    public static void main(String[] args) {
        // close the application context to shut down the custom ExecutorService
        logger.info(" Started Executing Application ");
        SpringApplication.run(Application.class, args).close();
        logger.info(" Completed Executing Application ");
    }
}
