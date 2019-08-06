/*
 * Copyright 2019 Apple. All Rights Reserved.
 *
 * This software is the proprietary information of Apple.
 * Use is subject to license terms.
 */
package com.apple.client.bean;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.apple.client.util.AppleClientTestConstants;

/**
 * Unit Test for ServiceResponse Bean.
 *
 * @author Kiran Kanaparthi
 * @since May 03, 2019
 */
public class ServiceResponseTest {

    private static final Logger logger = LoggerFactory.getLogger(ServiceResponseTest.class);
    
	 private static ServiceResponse serviceResponse;
	 
	 @Mock
	 private ServiceResponse mockResponse;

	/**
	 * Completes the initial setup with all mocks and objects.
	 * @throws Exception thrown if any exception during initialization.
	 */
	@BeforeClass
    public static void setUpBefore() throws Exception {
		serviceResponse = new ServiceResponse(AppleClientTestConstants.TEST_ELAPSED_TIME, 
				AppleClientTestConstants.TEST_RESPONSE_CODE);
    }
	
	/**
	 * Completes the initial setup with all mocks and objects.
	 * @throws Exception thrown if any exception during initialization.
	 */
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		logger.info(AppleClientTestConstants.MOCKS_INIT_COMPLETE);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link com.apple.client.bean.ServiceResponse#
	 * ServiceResponse(long, int)}.
	 */
	@Test
	public void testServiceResponseLongInt() {
		when(mockResponse.getResponseCode()).thenReturn(AppleClientTestConstants.TEST_RESPONSE_CODE);
		long actualValue = mockResponse.getResponseCode();
		verify(mockResponse,times(1)).getResponseCode();
		assertEquals(AppleClientTestConstants.TEST_RESPONSE_CODE, actualValue);
	}

	/**
	 * Test method for {@link com.apple.client.bean.ServiceResponse#
	 * ServiceResponse(int)}.
	 */
	@Test
	public void testServiceResponseInt() {
		when(mockResponse.getResponseCode()).thenReturn(AppleClientTestConstants.TEST_RESPONSE_CODE);
		mockResponse.setResponseCode(AppleClientTestConstants.TEST_RESPONSE_CODE);;
		verify(mockResponse,times(1)).setResponseCode(AppleClientTestConstants.TEST_RESPONSE_CODE);
		assertEquals(AppleClientTestConstants.TEST_RESPONSE_CODE, mockResponse.getResponseCode());
	}

	/**
	 * Test method for {@link com.apple.client.bean.ServiceResponse#getResponseCode()}.
	 */
	@Test
	public void testGetResponseCode() {
		when(mockResponse.getResponseCode()).thenReturn(AppleClientTestConstants.TEST_RESPONSE_CODE);
		mockResponse.setResponseCode(AppleClientTestConstants.TEST_RESPONSE_CODE);;
		verify(mockResponse,times(1)).setResponseCode(AppleClientTestConstants.TEST_RESPONSE_CODE);
		assertEquals(AppleClientTestConstants.TEST_RESPONSE_CODE, mockResponse.getResponseCode());
	}

	/**
	 * Test method for {@link com.apple.client.bean.ServiceResponse#
	 * setResponseCode(int)}.
	 */
	@Test
	public void testSetResponseCode() {
		serviceResponse.setResponseCode(AppleClientTestConstants.TEST_RESPONSE_CODE);;
		assertEquals(AppleClientTestConstants.TEST_RESPONSE_CODE,serviceResponse.getResponseCode());
	}

	/**
	 * Test method for {@link com.apple.client.bean.ServiceResponse#
	 * getElapsedTime()}.
	 */
	@Test
	public void testGetElapsedTime() {
		when(mockResponse.getElapsedTime()).thenReturn(AppleClientTestConstants.TEST_ELAPSED_TIME);
		mockResponse.getElapsedTime();
		verify(mockResponse,times(1)).getElapsedTime();
		assertEquals(AppleClientTestConstants.TEST_ELAPSED_TIME, mockResponse.getElapsedTime());
	}

	/**
	 * Test method for {@link com.apple.client.bean.ServiceResponse#
	 * setElapsedTime(long)}.
	 */
	@Test
	public void testSetElapsedTime() {
		serviceResponse.setElapsedTime(AppleClientTestConstants.TEST_ELAPSED_TIME);
		assertEquals(AppleClientTestConstants.TEST_ELAPSED_TIME,serviceResponse.getElapsedTime());
	}

	/**
	 * Test method for {@link com.apple.client.bean.ServiceResponse#
	 * toString()}.
	 */
	@Test
	public void testToString() {
		String testResponse = new StringBuilder().append("elapsedTime=").
				append(AppleClientTestConstants.TEST_ELAPSED_TIME).
		        append(", responseCode=").append(AppleClientTestConstants.TEST_RESPONSE_CODE).toString();
		String expectedValue = serviceResponse.toString();
		assertEquals(expectedValue, testResponse);
	}

}
