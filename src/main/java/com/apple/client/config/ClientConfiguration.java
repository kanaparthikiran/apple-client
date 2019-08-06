/*
 * Copyright 2019 Apple. All Rights Reserved.
 *
 * This software is the proprietary information of Apple.
 * Use is subject to license terms.
 */
package com.apple.client.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Client Http Pool Configuration for CloseableHttpClient.
 * It configures the Http Client Connection TimeOut, PoolSize,
 * Connection Timeout, Socket TimeOut. Reads all the values from
 * the configuration file.
 *
 * @author Kiran Kanaparthi
 * @since May 03, 2019
 */
@Component
@ConfigurationProperties(prefix = "httppool")
public class ClientConfiguration {

    private Integer maxPerRoute;
    private Integer maxTotal;
    private Integer connectionRequestTimeout;
    private Integer connectTimeout;
    private Integer socketTimeout;
    private boolean retryEnabled;
    private Integer retryCount;

    /**
     * @return the maxPerRoute
     */
    public Integer getMaxPerRoute() {
        return maxPerRoute;
    }
    /**
     * @param maxPerRoute the maxPerRoute to set
     */
    public void setMaxPerRoute(Integer maxPerRoute) {
        this.maxPerRoute = maxPerRoute;
    }
    /**
     * @return the maxTotal
     */
    public Integer getMaxTotal() {
        return maxTotal;
    }
    /**
     * @param maxTotal the maxTotal to set
     */
    public void setMaxTotal(Integer maxTotal) {
        this.maxTotal = maxTotal;
    }
    /**
     * @return the connectionRequestTimeout
     */
    public Integer getConnectionRequestTimeout() {
        return connectionRequestTimeout;
    }
    /**
     * @param connectionRequestTimeout the connectionRequestTimeout to set
     */
    public void setConnectionRequestTimeout(Integer connectionRequestTimeout) {
        this.connectionRequestTimeout = connectionRequestTimeout;
    }
    /**
     * @return the connectTimeout
     */
    public Integer getConnectTimeout() {
        return connectTimeout;
    }
    /**
     * @param connectTimeout the connectTimeout to set
     */
    public void setConnectTimeout(Integer connectTimeout) {
        this.connectTimeout = connectTimeout;
    }
    /**
     * @return the socketTimeout
     */
    public Integer getSocketTimeout() {
        return socketTimeout;
    }
    /**
    * @param socketTimeout the socketTimeout to set
    */
    public void setSocketTimeout(Integer socketTimeout) {
        this.socketTimeout = socketTimeout;
    }
    /**
     * @return the retryEnabled
     */
    public boolean isRetryEnabled() {
        return retryEnabled;
    }
    /**
     * @param retryEnabled the retryEnabled to set
     */
    public void setRetryEnabled(boolean retryEnabled) {
        this.retryEnabled = retryEnabled;
    }
    /**
     * @return the retryCount
     */
    public Integer getRetryCount() {
        return retryCount;
    }
    /**
     * @param retryCount the retryCount to set
     */
    public void setRetryCount(Integer retryCount) {
        this.retryCount = retryCount;
    }


}
