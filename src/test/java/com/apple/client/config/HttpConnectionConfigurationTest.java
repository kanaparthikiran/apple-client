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
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import javax.net.ssl.SSLContext;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.apple.client.config.HttpConnectionConfiguration;
import com.apple.client.config.ClientConfiguration;
import com.apple.client.util.AppleClientTestConstants;

/**
 * Unit test for HttpConnectionConfiguration.
 *
 * @author Kiran Kanaparthi
 * @since May 03, 2019
 */
public class HttpConnectionConfigurationTest {

    private static final Logger logger = LoggerFactory.getLogger
    		(HttpConnectionConfigurationTest.class);

	@InjectMocks
	private HttpConnectionConfiguration clientHttpPoolConfiguration;
	 
	@Mock
	private PoolingHttpClientConnectionManager poolingHttpClientConnectionManager;
	 
	@Mock
	private ClientConfiguration poolConfiguration;

	@Mock
	private RequestConfig requestConfig;
	
	@Mock
	private TrustStrategy acceptingTrustStrategy ;
	
	@Mock
	private SSLContext sslContext;
	
	@Mock
	private SSLConnectionSocketFactory csf;
	
	@Mock
	private CloseableHttpClient httpClient;
	    
	    
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		logger.info(AppleClientTestConstants.MOCKS_INIT_COMPLETE);
	}

	@After
	public void tearDown() throws Exception {
		clientHttpPoolConfiguration =  null;
		logger.info(AppleClientTestConstants.MOCKS_CLOSED);
	}
	
	
	/**
	 * 
	 */
	@Test
	public final void testPoolingHttpClientConnectionManager() {
		when(poolConfiguration.getMaxPerRoute()).thenReturn
			(AppleClientTestConstants.TEST_MAX_ROUTE_CONNECTIONS);
		when(poolConfiguration.getMaxTotal()).thenReturn
			(AppleClientTestConstants.TEST_MAX_TOTAL_CONNECTIONS);
		PoolingHttpClientConnectionManager actual = 
				clientHttpPoolConfiguration.poolingHttpClientConnectionManager();
		verify(poolConfiguration,times(1)).getMaxTotal();
		verify(poolConfiguration,times(1)).getMaxPerRoute();
		assertTrue(poolConfiguration.getMaxTotal()==actual.getMaxTotal());
		assertTrue(poolConfiguration.getMaxPerRoute()==actual.getDefaultMaxPerRoute());
	}

	/**
	 * 
	 */
	@Test
	public final void testRequestConfig() {
		when(poolConfiguration.getConnectionRequestTimeout()).thenReturn
			(AppleClientTestConstants.TEST_CONN_REQUEST_TIME_OUT);
		when(poolConfiguration.getConnectTimeout()).thenReturn
			(AppleClientTestConstants.TEST_CONNECTION_TIME_OUT);
		when(poolConfiguration.getSocketTimeout()).thenReturn
			(AppleClientTestConstants.TEST_SOCKET_TIME_OUT);
		RequestConfig actual = 
				clientHttpPoolConfiguration.requestConfig();
		verify(poolConfiguration,times(1)).getConnectionRequestTimeout();
		verify(poolConfiguration,times(1)).getConnectTimeout();
		verify(poolConfiguration,times(1)).getSocketTimeout();

		assertTrue(poolConfiguration.getConnectionRequestTimeout()==
				actual.getConnectionRequestTimeout());
		assertTrue(poolConfiguration.getConnectTimeout()==actual.getConnectTimeout());
		assertTrue(poolConfiguration.getSocketTimeout()==actual.getSocketTimeout());
		
	}

	/**
	 * Tests Http Client method.
	 * @throws KeyManagementException
	 * @throws NoSuchAlgorithmException
	 * @throws KeyStoreException
	 */
	@Test
	public final void testHttpClient() throws KeyManagementException,
			NoSuchAlgorithmException, KeyStoreException {
		when(poolConfiguration.getConnectionRequestTimeout()).thenReturn
		(AppleClientTestConstants.TEST_CONN_REQUEST_TIME_OUT);
		when(poolConfiguration.getConnectTimeout()).thenReturn
		(AppleClientTestConstants.TEST_CONNECTION_TIME_OUT);
		when(poolConfiguration.getSocketTimeout()).thenReturn
		(AppleClientTestConstants.TEST_SOCKET_TIME_OUT);
		assertTrue(clientHttpPoolConfiguration.httpClient
				(poolingHttpClientConnectionManager, requestConfig) instanceof
			CloseableHttpClient);
	}

}
