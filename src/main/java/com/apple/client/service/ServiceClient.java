/*
 * Copyright 2019 Apple. All Rights Reserved.
 *
 * This software is the proprietary information of Apple.
 * Use is subject to license terms.
 */
package com.apple.client.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.apple.client.bean.ServiceResponse;
import com.apple.client.exception.AppleClientException;
import com.apple.client.util.AppleStatsUtil;

/**
 * Business Layer class that implements the Service Operations
 *
 * @author Kiran Kanaparthi
 * @since May 03, 2019
 */
@Service
public class ServiceClient implements IServiceClient {

    private static final Logger logger = LoggerFactory.getLogger(ServiceClient.class);

    @Autowired
    private RestTemplate appleRestTemplate;


    /**
     * Invokes the given URL in asynchronous fashion.
     *
     * @param url url of the backend service.
     * @throws AppleClientException throws if any issues with the connection to the backend url.
     */
    @Async
    public CompletableFuture<ServiceResponse>  getServiceResponseAsync(String url)
            throws AppleClientException {
        try {
            long startTime = System.currentTimeMillis();
            ResponseEntity<String> response = appleRestTemplate.getForEntity(url, String.class);
            long elapsedTime = System.currentTimeMillis() - startTime;
            logger.info("Inside Method Status Code is {} Response Time {} ", response.getStatusCode(),
                   elapsedTime);
            ServiceResponse serviceResponse = new ServiceResponse(elapsedTime, response.getStatusCodeValue());
            return CompletableFuture.completedFuture(serviceResponse);
        }
        catch (Exception ex) {
            throw new AppleClientException(
                    "Exception while getting Service Response", ex);
        }
    }

    /**
     * Displays all the statistics for the data.
     *
     * @param totalSize size of the jobs.
     * @param result Completed jobs list for statistics.
     */
    @Override
     public void displayStatistics(int totalSize, List<ServiceResponse> result) {
        result.forEach(element->logger.debug("elements sorted {} ", element));

        long per10 = AppleStatsUtil.getPercentile(result, 10);
        long per50 = AppleStatsUtil.getPercentile(result, 50);
        long per90 = AppleStatsUtil.getPercentile(result, 90);
        long per95 = AppleStatsUtil.getPercentile(result, 95);
        long per99 = AppleStatsUtil.getPercentile(result, 99);

        logger.info("percentile 10 = {} ", per10);
        logger.info("percentile 50 = {} ", per50);
        logger.info("percentile 90 = {} ", per90);
        logger.info("percentile 95 = {} ", per95);
        logger.info("percentile 99 = {} ", per99);

        double mean = AppleStatsUtil.getMean(result);
        logger.info("The mean of all values = {}  ", mean);
        double standardDeviation = AppleStatsUtil.getStandardDeviation(result, mean);
        String standardDevFormatted = String.format("%.3f", standardDeviation);
        logger.info("The standardDeviation of all values = {} ", standardDevFormatted);
        double sumOfAllValues = result.stream().mapToDouble(x->x.getElapsedTime()).sum();
        logger.info("The Sum of time taken to execute all" +
            " tasks(if executed serially) is {} milli seconds ", sumOfAllValues);
    }
}

