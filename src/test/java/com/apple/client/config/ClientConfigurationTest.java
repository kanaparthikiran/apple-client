/*
 * Copyright 2019 Apple. All Rights Reserved.
 *
 * This software is the proprietary information of Apple.
 * Use is subject to license terms.
 */
package com.apple.client.config;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.apple.client.config.ClientConfiguration;
import com.apple.client.util.AppleClientTestConstants;

/**
 * Unit test for ClientConfiguration.
 *
 * @author Kiran Kanaparthi
 * @since May 03, 2019
 */
public class ClientConfigurationTest {


    private static final Logger logger = LoggerFactory.getLogger
    		(ClientConfigurationTest.class);

    
	private static ClientConfiguration httpConnectionPoolConfiguration;
	 
	@Mock
	private  ClientConfiguration mockHttpConnectionPoolConfiguration;

	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		httpConnectionPoolConfiguration = new ClientConfiguration();
		httpConnectionPoolConfiguration.
			setConnectionRequestTimeout(AppleClientTestConstants.TEST_CONN_REQUEST_TIME_OUT);
		httpConnectionPoolConfiguration.
			setConnectTimeout(AppleClientTestConstants.TEST_CONNECTION_TIME_OUT);
		httpConnectionPoolConfiguration.
			setMaxPerRoute(AppleClientTestConstants.TEST_MAX_ROUTE_CONNECTIONS);
		httpConnectionPoolConfiguration.
			setMaxTotal(AppleClientTestConstants.TEST_MAX_TOTAL_CONNECTIONS);
		httpConnectionPoolConfiguration.
			setSocketTimeout(AppleClientTestConstants.TEST_SOCKET_TIME_OUT);
	}
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		logger.info(AppleClientTestConstants.MOCKS_INIT_COMPLETE);
	}

	@After
	public void tearDown() throws Exception {
		mockHttpConnectionPoolConfiguration = null;
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		httpConnectionPoolConfiguration = null;
	}

	/**
	 * Test method for {@link com.apple.client.config.ClientConfiguration#
	 * getMaxPerRoute()}.
	 */
	@Test
	public final void testGetMaxPerRoute() {
		when(mockHttpConnectionPoolConfiguration.getMaxPerRoute()).
			thenReturn(AppleClientTestConstants.TEST_MAX_ROUTE_CONNECTIONS);
		mockHttpConnectionPoolConfiguration.getMaxPerRoute();
		verify(mockHttpConnectionPoolConfiguration,times(1)).getMaxPerRoute();
		assertTrue(AppleClientTestConstants.TEST_MAX_ROUTE_CONNECTIONS==
				httpConnectionPoolConfiguration.getMaxPerRoute());
	}

	/**
	 * Test method for {@link com.apple.client.config.ClientConfiguration#
	 * setMaxPerRoute(java.lang.Integer)}.
	 */
	@Test
	public final void testSetMaxPerRoute() {
		mockHttpConnectionPoolConfiguration.setMaxPerRoute
		    (AppleClientTestConstants.TEST_MAX_ROUTE_CONNECTIONS);
	    verify(mockHttpConnectionPoolConfiguration, times(1)).
	        setMaxPerRoute(AppleClientTestConstants.TEST_MAX_ROUTE_CONNECTIONS);
		assertTrue(AppleClientTestConstants.TEST_MAX_ROUTE_CONNECTIONS==
				httpConnectionPoolConfiguration.getMaxPerRoute());
	}

	/**
	 * Test method for {@link com.apple.client.config.ClientConfiguration#
	 * getMaxTotal()}.
	 */
	@Test
	public final void testGetMaxTotal() {
		when(mockHttpConnectionPoolConfiguration.getMaxTotal()).
		    thenReturn(AppleClientTestConstants.TEST_MAX_TOTAL_CONNECTIONS);
		assertTrue(AppleClientTestConstants.TEST_MAX_TOTAL_CONNECTIONS==
				mockHttpConnectionPoolConfiguration.getMaxTotal());
		assertTrue(AppleClientTestConstants.TEST_MAX_TOTAL_CONNECTIONS==
				httpConnectionPoolConfiguration.getMaxTotal());
	}

	/**
	 * Test method for {@link com.apple.client.config.ClientConfiguration#
	 * setMaxTotal(java.lang.Integer)}.
	 */
	@Test
	public final void testSetMaxTotal() {
		mockHttpConnectionPoolConfiguration.setMaxTotal
		    (AppleClientTestConstants.TEST_MAX_TOTAL_CONNECTIONS);
	    verify(mockHttpConnectionPoolConfiguration, times(1)).
	        setMaxTotal(AppleClientTestConstants.TEST_MAX_TOTAL_CONNECTIONS);
		assertTrue(AppleClientTestConstants.TEST_MAX_TOTAL_CONNECTIONS==
				httpConnectionPoolConfiguration.getMaxTotal());
	}

	/**
	 * Test method for {@link com.apple.client.config.ClientConfiguration#
	 * getConnectionRequestTimeout()}.
	 */
	@Test
	public final void testGetConnectionRequestTimeout() {
		when(mockHttpConnectionPoolConfiguration.getConnectionRequestTimeout()).
		thenReturn(AppleClientTestConstants.TEST_CONN_REQUEST_TIME_OUT);
		assertTrue(AppleClientTestConstants.TEST_CONN_REQUEST_TIME_OUT==
				mockHttpConnectionPoolConfiguration.getConnectionRequestTimeout());
		assertTrue(AppleClientTestConstants.TEST_CONN_REQUEST_TIME_OUT==
				httpConnectionPoolConfiguration.getConnectionRequestTimeout());
	}

	/**
	 * Test method for {@link com.apple.client.config.ClientConfiguration#
	 * setConnectionRequestTimeout(java.lang.Integer)}.
	 */
	@Test
	public final void testSetConnectionRequestTimeout() {
		mockHttpConnectionPoolConfiguration.setConnectionRequestTimeout
		(AppleClientTestConstants.TEST_CONN_REQUEST_TIME_OUT);
	    verify(mockHttpConnectionPoolConfiguration, times(1)).setConnectionRequestTimeout
	    (AppleClientTestConstants.TEST_CONN_REQUEST_TIME_OUT);
		assertTrue(AppleClientTestConstants.TEST_CONN_REQUEST_TIME_OUT==
				httpConnectionPoolConfiguration.getConnectionRequestTimeout());
	}

	/**
	 * Test method for {@link com.apple.client.config.ClientConfiguration#
	 * getConnectTimeout()}.
	 */
	@Test
	public final void testGetConnectTimeout() {
		mockHttpConnectionPoolConfiguration.getConnectTimeout();
		verify(mockHttpConnectionPoolConfiguration, times(1)).getConnectTimeout();
		assertTrue(AppleClientTestConstants.TEST_CONNECTION_TIME_OUT==
			httpConnectionPoolConfiguration.getConnectTimeout());
	}

	/**
	 * Test method for {@link com.apple.client.config.ClientConfiguration#
	 * setConnectTimeout(java.lang.Integer)}.
	 */
	@Test
	public final void testSetConnectTimeout() {
		mockHttpConnectionPoolConfiguration.setConnectTimeout
		    (AppleClientTestConstants.TEST_CONNECTION_TIME_OUT);
	    verify(mockHttpConnectionPoolConfiguration, times(1)).
	        setConnectTimeout(AppleClientTestConstants.TEST_CONNECTION_TIME_OUT);
		assertTrue(AppleClientTestConstants.TEST_CONNECTION_TIME_OUT==
				httpConnectionPoolConfiguration.getConnectTimeout());
	}

	/**
	 * Test method for {@link com.apple.client.config.ClientConfiguration#
	 * getSocketTimeout()}.
	 */
	@Test
	public final void testGetSocketTimeout() {
		when(mockHttpConnectionPoolConfiguration.getSocketTimeout()).
		    thenReturn(AppleClientTestConstants.TEST_SOCKET_TIME_OUT);
		assertTrue(AppleClientTestConstants.TEST_SOCKET_TIME_OUT==
				mockHttpConnectionPoolConfiguration.getSocketTimeout());
		assertTrue(AppleClientTestConstants.TEST_SOCKET_TIME_OUT==
				httpConnectionPoolConfiguration.getSocketTimeout());
	}

	/**
	 * Test method for {@link com.apple.client.config.ClientConfiguration#
	 * setSocketTimeout(java.lang.Integer)}.
	 */
	@Test
	public final void testSetSocketTimeout() {
		mockHttpConnectionPoolConfiguration.setSocketTimeout
		    (AppleClientTestConstants.TEST_SOCKET_TIME_OUT);
	    verify(mockHttpConnectionPoolConfiguration, times(1)).
	        setSocketTimeout(AppleClientTestConstants.TEST_SOCKET_TIME_OUT);
		assertTrue(AppleClientTestConstants.TEST_SOCKET_TIME_OUT==
				httpConnectionPoolConfiguration.getSocketTimeout());
	}

}
