/*
 * Copyright 2019 Apple. All Rights Reserved.
 *
 * This software is the proprietary information of Apple.
 * Use is subject to license terms.
 */
package com.apple.client.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.apple.client.bean.ServiceResponse;
import com.apple.client.exception.AppleClientException;

/**
 * Business Layer interface that defines the Service Operations
 *
 * @author Kiran Kanaparthi
 * @since May 03, 2019
 */
public interface IServiceClient {

    void displayStatistics(int totalSize, List<ServiceResponse> result);
    CompletableFuture<ServiceResponse>  getServiceResponseAsync(String url) throws AppleClientException;
}
