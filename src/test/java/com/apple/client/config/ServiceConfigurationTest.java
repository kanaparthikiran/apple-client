/*
 * Copyright 2019 Apple. All Rights Reserved.
 *
 * This software is the proprietary information of Apple.
 * Use is subject to license terms.
 */
package com.apple.client.config;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.apple.client.config.ServiceConfiguration;
import com.apple.client.util.AppleClientTestConstants;

/**
 * Unit test for ServiceConfiguration.
 *
 * @author Kiran Kanaparthi
 * @since May 03, 2019
 */
public class ServiceConfigurationTest {

    private static final Logger logger = LoggerFactory.getLogger(ServiceConfigurationTest.class);
    
	 private ServiceConfiguration config;
	 
	/**
	 * Completes the initial setup with all mocks and objects.
	 * @throws Exception thrown if any exception during initialization.
	 */
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		logger.info(AppleClientTestConstants.MOCKS_INIT_COMPLETE);
		config = new ServiceConfiguration();
	}

	/**
	 * Closes all objects and streams.
	 * @throws Exception 
	 */
	@After
	public void tearDown() throws Exception {
		logger.info(AppleClientTestConstants.MOCKS_CLOSED);
	}

	/**
	 * Tests the getURL method.
	 */
	@Test
	public final void testGetUrl() {
		config.setUrl(AppleClientTestConstants.TEST_SERVICE_URL);
		assertEquals(AppleClientTestConstants.TEST_SERVICE_URL,config.getUrl());
	}

	/**
	 * Tests the setURL method.
	 */
	@Test
	public final void testSetUrl() {
		config.setUrl(AppleClientTestConstants.TEST_SERVICE_URL);
		assertEquals(AppleClientTestConstants.TEST_SERVICE_URL,config.getUrl());
	}

}
