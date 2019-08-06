/*
 * Copyright 2019 Apple. All Rights Reserved.
 *
 * This software is the proprietary information of Apple.
 * Use is subject to license terms.
 */
package com.apple.client;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import com.apple.client.bean.ServiceResponse;
import com.apple.client.config.ServiceConfiguration;
import com.apple.client.exception.AppleClientException;
import com.apple.client.service.ServiceClient;
import com.apple.client.util.AppleClientTestConstants;

/**
 * Unit test for Application Command Line Runner.
 *
 * @author Kiran Kanaparthi
 * @param <T>
 * @since May 03, 2019
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class AppRunnerTest {
	
     private static final Logger logger = LoggerFactory.getLogger(AppRunnerTest.class);
    
	 @InjectMocks
	 private AppRunner appRunner;
	
	 @Mock
	 private RestTemplate restTemplate;

	 @Mock
	 private ServiceConfiguration config;
	 
	 @Mock
	 private ServiceClient httpClient;
	 
	 @Mock
	 private RestTemplateBuilder restTemplateBuilder;
	 
	 @Mock
	 private HttpComponentsClientHttpRequestFactory clientRequestFactory;
	 
	 @Mock 
	 private CompletableFuture<ServiceResponse>  completableFeature;
	 /**
  	  * Sets up the default configuration for all tests
	  * 
	  * @throws java.lang.Exception
	  */
  	 @Before
	 public void setUp() throws Exception {
	 	MockitoAnnotations.initMocks(this);
	 	logger.info(AppleClientTestConstants.MOCKS_INITIALIZED);
	 }
		
	 @After
	  public void tearDown() {
	   	appRunner = null;
     }
	 
	/**
	 * Test method for {@link com.apple.client.AppRunner
	 * #executeJob()}.
	 * 
	 * Tests the success scenario for  executeJob
	 * @param <T>
	 * @throws AppleClientException 
	 */
	@Test
	public void testGetJobResults() throws AppleClientException  {
	        when(restTemplateBuilder.build()).thenReturn(restTemplate);
	        Mockito.when(restTemplateBuilder.build().getForEntity
	        		(AppleClientTestConstants.TEST_SERVICE_URL, String.class)).thenReturn
		     	(new ResponseEntity<String>(AppleClientTestConstants.TEST_RESPONSE_SUCCESS,
		     			HttpStatus.OK));
	        when(httpClient.getServiceResponseAsync(AppleClientTestConstants.TEST_SERVICE_URL)).
	        	thenReturn(completableFeature);
	        when(config.getUrl()).thenReturn(AppleClientTestConstants.TEST_SERVICE_URL);
	        List<CompletableFuture<ServiceResponse>> expected = 
	        		appRunner.getJobResults(AppleClientTestConstants.TEST_BATCH_SIZE, 
	        				AppleClientTestConstants.TEST_TOTAL_JOBS, 
	        				AppleClientTestConstants.TEST_TOTAL_BATCHES);
 	        verify(httpClient,times(AppleClientTestConstants.TEST_TOTAL_JOBS)).
 	        getServiceResponseAsync
 	           (AppleClientTestConstants.TEST_SERVICE_URL);
	        assertTrue(expected.size()==AppleClientTestConstants.TEST_TOTAL_JOBS);
	}
		
	/**
	 * Test method for {@link com.apple.client.AppRunner
	 * #executeJob()}.
	 * 
	 * Tests the success scenario for executeJob
	 * @throws AppleClientException 
	 */
	@Test
	public void testExecuteJob() throws AppleClientException  {
	        when(restTemplateBuilder.build()).thenReturn(restTemplate);
	        Mockito.when(restTemplateBuilder.build().getForEntity
	        		(AppleClientTestConstants.TEST_SERVICE_URL, String.class)).thenReturn
		     	(new ResponseEntity<String>(AppleClientTestConstants.TEST_RESPONSE_SUCCESS,
		     			HttpStatus.OK));
	        
	        appRunner.batchTasks(AppleClientTestConstants.TEST_SERVICE_URL, 
	       		AppleClientTestConstants.TEST_BATCH_SIZE);
		
	 	    verify(httpClient,times(AppleClientTestConstants.TEST_BATCH_SIZE)).
	 	    getServiceResponseAsync
	 	           (AppleClientTestConstants.TEST_SERVICE_URL);
			
	}
	
	

	
	/**
	 * Test method for {@link com.apple.client.AppRunner
	 * #executeJob()}.
	 * 
	 * Tests the success scenario for executeJob
	 * @throws AppleClientException 
	 */
	@Test(expected=AppleClientException.class)
	public void testExecuteJobException() throws AppleClientException  {
	        when(restTemplateBuilder.build()).thenReturn(restTemplate);
	        when(httpClient.getServiceResponseAsync(null)).
	        	thenThrow(AppleClientException.class);
	        appRunner.batchTasks(null, 
	       		AppleClientTestConstants.TEST_BATCH_SIZE);
	}
}