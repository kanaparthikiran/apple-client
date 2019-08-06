/*
 * Copyright 2019 Apple. All Rights Reserved.
 *
 * This software is the proprietary information of Apple.
 * Use is subject to license terms.
 */
package com.apple.client.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration for Backend service properties
 *
 * @author Kiran Kanaparthi
 * @since May 03, 2019
 */
@Configuration
@ConfigurationProperties(prefix = "backend.service")
public class ServiceConfiguration {

    //Backend service Url.
    private String url;
    private int batch;
    private int total;
    private int depth;
    
    /**
     * @return the batch
     */
    public int getBatch() {
        return batch;
    }

    /**
     * @param batch the batch to set
     */
    public void setBatch(int batch) {
        this.batch = batch;
    }

    /**
     * @return the total
     */
    public int getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(int total) {
        this.total = total;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

	/**
	 * @return the depth
	 */
	public int getDepth() {
		return depth;
	}

	/**
	 * @param depth the depth to set
	 */
	public void setDepth(int depth) {
		this.depth = depth;
	}

}
