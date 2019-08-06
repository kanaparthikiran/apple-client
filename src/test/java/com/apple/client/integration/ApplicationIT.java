/*
 * Copyright 2019 Apple. All Rights Reserved.
 *
 * This software is the proprietary information of Apple.
 * Use is subject to license terms.
 */
package com.apple.client.integration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.apple.client.util.AppleClientTestConstants;

/**
 * Integration test for Apple Http Client.
 *
 * @author Kiran Kanaparthi
 * @param <T>
 * @since May 03, 2019
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationIT {
    private static final Logger logger = LoggerFactory.getLogger(ApplicationIT.class);

	/**
	 * Loads the Application Context and Initializes the Application 
	 * for Integration Test
	 */
	@Test
	public void contextLoads() {
		logger.info(AppleClientTestConstants.SPRING_CONTEXT_LOADED);
	}
}
