/*
 * Copyright 2019 Apple. All Rights Reserved.
 *
 * This software is the proprietary information of Apple.
 * Use is subject to license terms.
 */
package com.apple.client.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import com.apple.client.bean.ServiceResponse;
import com.apple.client.exception.AppleClientException;
import com.apple.client.util.AppleClientTestConstants;

/**
 * Unit Test for App Config to validate restTemplate creation.
 *
 * @author Kiran Kanaparthi
 * @since May 03, 2019
 */
public class ServiceClientTest {

    private static final Logger logger = LoggerFactory.getLogger(ServiceClientTest.class);

	 @InjectMocks
	 private ServiceClient serviceClient;
	 
	 @Mock
	 private RestTemplate restTemplate;

	 @Mock
	 private CloseableHttpClient httpClient;
	 
	 @Mock
	 private RestTemplateBuilder restTemplateBuilder;
	 
	 @Mock
	 private HttpComponentsClientHttpRequestFactory clientHttpRequestFactory;
	 
	 @Mock
	 private ResponseEntity<String> responseEntity = new ResponseEntity<>(HttpStatus.OK);
	 
	 @Mock
	 private CompletableFuture<ServiceResponse> response;
	 
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
	}

	/**
	 * Test method for {@link com.apple.client.service.ServiceClient#
	 * getServiceResponseAsync(java.lang.String)}.
	 * @throws AppleClientException 
	 */
	@Test
	public void testGetServiceResponseAsync() throws AppleClientException {
		when(restTemplate.getForEntity(AppleClientTestConstants.TEST_SERVICE_URL, String.class)).
			thenReturn(responseEntity);
		when(responseEntity.getStatusCode()).thenReturn(HttpStatus.OK);
		CompletableFuture<ServiceResponse> actualResponse = 
		serviceClient.getServiceResponseAsync(AppleClientTestConstants.TEST_SERVICE_URL);
		verify(restTemplate,times(1)).getForEntity(AppleClientTestConstants.TEST_SERVICE_URL,
				String.class);
		assertEquals(java.util.concurrent.CompletableFuture.class,actualResponse.getClass());
		
	}

	/**
	 * Test method for {@link com.apple.client.service.ServiceClient#
	 * displayStatistics(int, java.util.List)}.
	 */
	@Test
	public void testDisplayStatistics() {
		List<ServiceResponse> result = new ArrayList<>();
		ServiceResponse responseOne = new ServiceResponse(100, HttpStatus.OK.value());
		ServiceResponse responseTwo = new ServiceResponse(200, HttpStatus.OK.value());
		ServiceResponse responseThree = new ServiceResponse(300, HttpStatus.OK.value());
		result.add(responseOne);
		result.add(responseTwo);
		result.add(responseThree);
		serviceClient.displayStatistics(AppleClientTestConstants.TEST_TOTAL_JOBS, result);
		assertTrue(result.stream().mapToDouble(x->x.getElapsedTime()).sum()==600);
	}


}
