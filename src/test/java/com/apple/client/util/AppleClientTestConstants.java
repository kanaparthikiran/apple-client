/*
 * Copyright 2019 Apple. All Rights Reserved.
 *
 * This software is the proprietary information of Apple.
 * Use is subject to license terms.
 */
package com.apple.client.util;

import org.springframework.http.HttpStatus;

/**
 * Unit Test constants for the Apple Client
 *
 * @author Kiran Kanaparthi
 * @since May 03, 2019
 */
public class AppleClientTestConstants {
	
	public static final String TEST_RESPONSE_SUCCESS = "SUCCESS";
	public static final String TEST_RESPONSE_ERROR = "ERROR";
	public static final String TEST_SERVICE_URL = "https://en.wikipedia.org/wiki/Main_Page";
	public static final String MOCKS_INIT_COMPLETE = 
			"Initialized all Mocks and Injected in to the instance";
	public static final int TEST_BATCH_SIZE = 20;
	public static final int TEST_TOTAL_JOBS = 100;
	public static final int TEST_TOTAL_BATCHES = 5;
	public static final int TEST_MAX_ROUTE_CONNECTIONS = 10;
	public static final int TEST_MAX_TOTAL_CONNECTIONS = 20;
	public static final int TEST_CONN_REQUEST_TIME_OUT = 5;
	public static final int TEST_CONNECTION_TIME_OUT = 5;
	public static final int TEST_SOCKET_TIME_OUT = 5;
	public static final int TEST_CONN_SOCKET_TIME_OUT = 3;
	
	public static final String MOCKS_INITIALIZED = "Initialized all mocks and objects";
	public static final String MOCKS_CLOSED = "Closed all mocks and objects ";
	public static final String SPRING_CONTEXT_LOADED = "Loaded Spring Application Context";

	public static final long TEST_ELAPSED_TIME = 100;
	public static final int TEST_RESPONSE_CODE = HttpStatus.OK.value();

}
