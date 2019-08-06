/*
 * Copyright 2019 Apple. All Rights Reserved.
 *
 * This software is the proprietary information of Apple.
 * Use is subject to license terms.
 */
package com.apple.client;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.apple.client.bean.ServiceResponse;
import com.apple.client.config.ServiceConfiguration;
import com.apple.client.exception.AppleClientException;
import com.apple.client.service.IServiceClient;
import com.apple.client.util.ServiceResponseComparator;
/**
 * Application Command Line Runner for the Apple Client Application
 *
 * @author Kiran Kanaparthi
 * @since May 03, 2019
 */
@Component
public class AppRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(AppRunner.class);

    @Autowired
    private IServiceClient httpClient;

    @Autowired
    private ServiceConfiguration config;

    /**
     * Executes the list of tasks asynchronously with the given batch size.
     *
     * @param url url of the backend service.
     * @param batchSize size for one batch.
     * @return the feature objects for the submitted jobs.
     * @throws AppleClientException thrown if there is any exception in job processing.
     */
    public List<CompletableFuture<ServiceResponse>> batchTasks(String url, int batchSize)
        throws AppleClientException {
        List<CompletableFuture<ServiceResponse>> completeResponses = new ArrayList<>();
        for (int i = 0; i < batchSize; i++) {
            completeResponses.add(httpClient.getServiceResponseAsync(url));
        }
        return completeResponses;
    }

    /**
     * Executes the Spring Application from Command Line.
     *
     * @throws AppleClientException thrown if there is any exception in job processing.
     */
    @Override
    public void run(String... args) throws AppleClientException {
        // Start the clock
        long start = System.currentTimeMillis();

        int batchSize = config.getBatch();
        int totalSize = config.getTotal();
        int totalBatches = totalSize / batchSize;
        

        List<CompletableFuture<ServiceResponse>> allResponses =
            getJobResults(batchSize, totalSize, totalBatches);

         // Wait until the jobs are all done, to get metrics.
        List<ServiceResponse> result =
                allResponses.stream()
                        .map(CompletableFuture::join)
                        .collect(Collectors.toList());

        logger.info("\nTime taken to Execute the tasks parallelly is {} milli seconds \n",
                System.currentTimeMillis() - start);
        //Sort the collection for Percentile, and Standard Deviation
        Collections.sort(result, new ServiceResponseComparator<>());

        httpClient.displayStatistics(totalSize, result);
    }

    /**
    * Processes all the Jobs using several batches with the given batch size.
    *
    * If the batch size divide the total Jobs with a
    * remainder, the remainder of the Jobs are also processed in the end.
    *
    * @param batchSize Batch Size for the jobs.
    * @param totalBatches Number of batches in which the total jobs are divided into.
    * @return returns the List of completed Jobs with the responsetime, and response codes.
    * @throws AppleClientException thrown in case of any exception in running the job.
    */
    public List<CompletableFuture<ServiceResponse>> getJobResults(int batchSize, int totalSize, int totalBatches)
        throws AppleClientException {
        //Incase of the batchSize(it is configurable, if this is say 15) does not divide 100,
        //there could be some remaining
        //items from the list of batches, process the left out jobs in the final batch.
        int remainingFromTotal = totalSize - (totalBatches * batchSize);
        List<CompletableFuture<ServiceResponse>>  allResponses  = new ArrayList<>();

        // Kick off multiple, asynchronous lookups
        for (int i = 0; i < totalBatches; i++) {
            List<CompletableFuture<ServiceResponse>>  batchResponses =
                batchTasks(config.getUrl(), batchSize);
            allResponses.addAll(batchResponses);
        }

        //Process the remaining jobs, that might have slipped from the batch
        //if the batch size does not divide total jobs(100).
        if (remainingFromTotal > 0) {
            List<CompletableFuture<ServiceResponse>>  remainingItems =
                batchTasks(config.getUrl(), remainingFromTotal);
            allResponses.addAll(remainingItems);
        }
        return allResponses;
    }
}
