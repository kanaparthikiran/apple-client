/*
 * Copyright 2019 Apple. All Rights Reserved.
 *
 * This software is the proprietary information of Apple.
 * Use is subject to license terms.
 */
package com.apple.client.util;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import com.apple.client.bean.ServiceResponse;

/**
 * Unit Test constants for the Apple Client
 *
 * @author Kiran Kanaparthi
 * @param <T> Type of the Object under test
 * @param <T> Type of the Object used for comparison
 * @since May 03, 2019
 */
public class ServiceResponseComparatorTest {

    private static final Logger logger = LoggerFactory.getLogger(ServiceResponseComparatorTest.class);
    
	// @Mock
	 private ServiceResponseComparator<?> comparator = null;
	 
	/**
	 * Completes the initial setup with all mocks and objects.
	 * @throws Exception thrown if any exception during initialization.
	 */
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		logger.info(AppleClientTestConstants.MOCKS_INIT_COMPLETE);
		comparator = new ServiceResponseComparator<>();
	}

	/**
	 * Test method for {@link com.apple.client.util.ServiceResponseComparator#
	 * compare(com.apple.client.bean.ServiceResponse, com.apple.client.bean.ServiceResponse)}.
	 */
	@Test
	public void testCompareGreater() {
		ServiceResponse response1 = new ServiceResponse(300l, HttpStatus.OK.value());
		ServiceResponse response2 = new ServiceResponse(200l, HttpStatus.OK.value());
		//when(comparator.compare(response1, response2)).thenReturn(1);
		comparator.compare(response1, response2);
		//verify(comparator,times(1)).compare(response1, response2);
		logger.info(" comparator.compare(response1, response2) is "+comparator.compare(response1, response2));
		assertTrue(comparator.compare(response1, response2)>=1);
	}
	/**
	 * Test method for {@link com.apple.client.util.ServiceResponseComparator#
	 * compare(com.apple.client.bean.ServiceResponse, com.apple.client.bean.ServiceResponse)}.
	 */
	@Test
	public void testCompareEqual() {
		ServiceResponse response1 = new ServiceResponse(300l, HttpStatus.OK.value());
		ServiceResponse response2 = new ServiceResponse(300l, HttpStatus.OK.value());
		//when(comparator.compare(response1, response2)).thenReturn(0);
		comparator.compare(response1, response2);
		//verify(comparator,times(1)).compare(response1, response2);
		assertTrue(comparator.compare(response1, response2)==0);
	}
	/**
	 * Test method for {@link com.apple.client.util.ServiceResponseComparator#
	 * compare(com.apple.client.bean.ServiceResponse, com.apple.client.bean.ServiceResponse)}.
	 */
	@Test
	public void testCompareLess() {
		ServiceResponse response1 = new ServiceResponse(300l, HttpStatus.OK.value());
		ServiceResponse response2 = new ServiceResponse(400l, HttpStatus.OK.value());
		//when(comparator.compare(response1, response2)).thenReturn(-1);
		comparator.compare(response1, response2);
		//verify(comparator,times(1)).compare(response1, response2);
		assertTrue(comparator.compare(response1, response2)<=-1);
	}
	
	/**
	 * Test method for {@link com.apple.client.util.ServiceResponseComparator#
	 * compare(com.apple.client.bean.ServiceResponse, com.apple.client.bean.ServiceResponse)}.
	 */
	@Test
	public void testCompareNullFirst() {
		ServiceResponse response1 = null;
		ServiceResponse response2 = new ServiceResponse(400l, HttpStatus.OK.value());
		//when(comparator.compare(response1, response2)).thenReturn(0);
		comparator.compare(response1, response2);
		//verify(comparator,times(1)).compare(response1, response2);
		assertTrue(comparator.compare(response1, response2)==0);
	}

	/**
	 * Test method for {@link com.apple.client.util.ServiceResponseComparator#
	 * compare(com.apple.client.bean.ServiceResponse, com.apple.client.bean.ServiceResponse)}.
	 */
	@Test
	public void testCompareNullSecond() {
		ServiceResponse response1 = new ServiceResponse(300l, HttpStatus.OK.value());
		ServiceResponse response2 = null;
		//when(comparator.compare(response1, response2)).thenReturn(0);
		comparator.compare(response1, response2);
		//verify(comparator,times(1)).compare(response1, response2);
		assertTrue(comparator.compare(response1, response2)==0);
	}
	
	/**
	 * Test method for {@link com.apple.client.util.ServiceResponseComparator#
	 * compare(com.apple.client.bean.ServiceResponse, com.apple.client.bean.ServiceResponse)}.
	 */
	@Test
	public void testCompareNullBoth() {
		ServiceResponse response1 = null;
		ServiceResponse response2 = null;
		//when(comparator.compare(response1, response2)).thenReturn(0);
		comparator.compare(response1, response2);
		//verify(comparator,times(1)).compare(response1, response2);
		assertTrue(comparator.compare(response1, response2)==0);
	}
	
}
