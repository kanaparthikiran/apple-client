/*
 * Copyright 2019 Apple. All Rights Reserved.
 *
 * This software is the proprietary information of Apple.
 * Use is subject to license terms.
 */
package com.apple.client.config;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

import org.apache.http.impl.client.CloseableHttpClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import com.apple.client.config.AppConfig;
import com.apple.client.util.AppleClientTestConstants;

/**
 * Unit Test for App Config to validate restTemplate creation.
 *
 * @author Kiran Kanaparthi
 * @since May 03, 2019
 */
public class AppConfigTest {

    private static final Logger logger = LoggerFactory.getLogger(AppConfigTest.class);

	 @InjectMocks
	 private AppConfig appConfig;
	 
	 @Mock
	 private RestTemplate restTemplate;

	 @Mock
	 private CloseableHttpClient httpClient;
	 
	 @Mock
	 private RestTemplateBuilder restTemplateBuilder;
	 
	 @Mock
	 private HttpComponentsClientHttpRequestFactory clientHttpRequestFactory;
	 
	/**
	 * @throws java.lang.Exception
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
		logger.info(AppleClientTestConstants.MOCKS_CLOSED);
	}

	/**
	 * Test method for {@link com.apple.client.config.AppConfig#
	 * restTemplate(org.apache.http.impl.client.CloseableHttpClient)}.
	 * @throws NoSuchAlgorithmException 
	 * @throws KeyStoreException 
	 * @throws KeyManagementException 
	 */
	@Test
	public final void testRestTemplate() throws KeyManagementException, 
							KeyStoreException, NoSuchAlgorithmException {
		when(restTemplateBuilder.build()).thenReturn(restTemplate);
		clientHttpRequestFactory.setHttpClient(httpClient);
		verify(clientHttpRequestFactory,times(1)).setHttpClient(httpClient);
		assertEquals(restTemplateBuilder.build(),restTemplate);
		when(new RestTemplate(clientHttpRequestFactory)).thenReturn(restTemplate);
	    assertEquals(appConfig.restTemplate().getClass(),
	            org.springframework.web.client.RestTemplate.class);
	}
	
	
    @Bean
    public RestTemplate restTemplate() throws KeyStoreException,
        NoSuchAlgorithmException, KeyManagementException {
        HttpComponentsClientHttpRequestFactory requestFactory =
                        new HttpComponentsClientHttpRequestFactory();
        requestFactory.setHttpClient(httpClient);
        return  new RestTemplate(requestFactory);
    }

}

