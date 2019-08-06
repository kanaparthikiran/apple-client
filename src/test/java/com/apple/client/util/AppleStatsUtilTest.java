/*
 * Copyright 2019 Apple. All Rights Reserved.
 *
 * This software is the proprietary information of Apple.
 * Use is subject to license terms.
 */
package com.apple.client.util;

import static org.junit.Assert.assertTrue;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;

import com.apple.client.bean.ServiceResponse;

/**
 * Unit Test for the Apple Client Util class.
 *
 * @author Kiran Kanaparthi
 * @since May 03, 2019
 */
public class AppleStatsUtilTest {

    private static final Logger logger = LoggerFactory.getLogger(AppleStatsUtilTest.class);
    
	private static List<ServiceResponse> data = new ArrayList<>();
    
	/**
	 * Completes the initial setup with all mocks and objects.
	 * @throws Exception thrown if any exception during initialization.
	 */
	@BeforeClass
    public static void setUpBefore() throws Exception {
		ServiceResponse response1 = new ServiceResponse(100l, HttpStatus.OK.value());
		ServiceResponse respons2 = new ServiceResponse(200l, HttpStatus.OK.value());
		ServiceResponse response3 = new ServiceResponse(300l, HttpStatus.OK.value());
		data.add(response1);
		data.add(respons2);
		data.add(response3);
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
	 * Test method for {@link com.apple.client.util.AppleStatsUtil#
	 * getStandardDeviation(java.util.List, double)}.
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	@Test(expected=InvocationTargetException.class)
	public void testException() throws NoSuchMethodException, SecurityException, InstantiationException, 
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Constructor<?> constructor  = AppleStatsUtil.class.getDeclaredConstructor();
		assertTrue(Modifier.isPrivate(constructor.getModifiers()));
		constructor.setAccessible(true);
		constructor.newInstance();
	}
	
	/**
	 * Test method for {@link com.apple.client.util.AppleStatsUtil#
	 * getStandardDeviation(java.util.List, double)}.
	 */
	@Test
	public void testGetStandardDeviation() {
		double mean = 200;
		double actualStandardDeviation = AppleStatsUtil.getStandardDeviation(data, mean);
		double expectedStandardDeviation = 100;
		logger.info("actualStandardDeviation "+actualStandardDeviation);
		assertTrue(actualStandardDeviation==expectedStandardDeviation);
	}
	
	/**
	 * Test method for {@link com.apple.client.util.AppleStatsUtil#
	 * getStandardDeviation(java.util.List, double)}.
	 */
	@Test
	public void testGetStandardDeviationEmptyCollection() {
		double mean = 200;
		double actualStandardDeviation = AppleStatsUtil.getStandardDeviation(null, mean);
		double expectedStandardDeviation = 0;
		logger.info("actualStandardDeviation "+actualStandardDeviation);
		assertTrue(actualStandardDeviation==expectedStandardDeviation);
	}
	
	/**
	 * Test method for {@link com.apple.client.util.AppleStatsUtil#
	 * getMean(java.util.List)}.
	 */
	@Test
	public void testGetMean() {
		double actualMean = AppleStatsUtil.getMean(data);
		double expectedMean = 0.0;
        if (!CollectionUtils.isEmpty(data)) {
        	expectedMean = (data.stream().mapToDouble(
                x->x.getElapsedTime()).sum()) / data.size();
        }
		assertTrue(actualMean==expectedMean);
	}

	/**
	 * Test method for {@link com.apple.client.util.AppleStatsUtil#
	 * getMean(java.util.List)}.
	 */
	@Test
	public void testGetMeanEmptyCollection() {
		double actualMean = AppleStatsUtil.getMean(null);
		double expectedMean = 0.0;
		assertTrue(actualMean==expectedMean);
	}
	/**
	 * Test method for {@link com.apple.client.util.AppleStatsUtil#
	 * getPercentile(java.util.List, int)}.
	 */
	@Test
	public void testGetPercentileLeast() {
		long actualPercentile = AppleStatsUtil.getPercentile(data, 10);
		int expectedPercentile = 100;
		assertTrue(actualPercentile==expectedPercentile);
	}

	/**
	 * Test method for {@link com.apple.client.util.AppleStatsUtil#
	 * getPercentile(java.util.List, int)}.
	 */
	@Test
	public void testGetPercentileLeastEmptyCollection() {
		long actualPercentile = AppleStatsUtil.getPercentile(null, 10);
		int expectedPercentile = 0;
		assertTrue(actualPercentile==expectedPercentile);
	}
	
	/**
	 * Test method for {@link com.apple.client.util.AppleStatsUtil#
	 * getPercentile(java.util.List, int)}.
	 */
	@Test
	public void testGetPercentileTop() {
		long actualPercentile = AppleStatsUtil.getPercentile(data, 1000);
		int expectedPercentile = 0;
		assertTrue(actualPercentile==expectedPercentile);
	}
}
