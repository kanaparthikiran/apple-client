/*
 * Copyright 2019 Apple. All Rights Reserved.
 *
 * This software is the proprietary information of Apple.
 * Use is subject to license terms.
 */
package com.apple.client.bean;

/**
 * Bean class that is used to represent the Service Response.
 *
 * @author Kiran Kanaparthi
 * @since May 03, 2019
 */
public class ServiceResponse {

    private long elapsedTime;
    private int responseCode;

    /**
     *
     * @param elapsedTime elapsed Time from the start of the request execution.
     * @param responseCode response code returned by the service.
     */
    public ServiceResponse(long elapsedTime, int responseCode) {
        super();
        this.elapsedTime = elapsedTime;
        this.responseCode = responseCode;
    }

    public ServiceResponse(int responseCode) {
        super();
        this.responseCode = responseCode;
    }

    /**
     * @return the responseCode
     */
    public int getResponseCode() {
        return responseCode;
    }
    /**
     * @param responseCode the responseCode to set
     */
    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }
    /**
     * @return the elapsedTime
     */
    public long getElapsedTime() {
        return elapsedTime;
    }
    /**
     * @param elapsedTime the elapsedTime to set
     */
    public void setElapsedTime(long elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    /**
     * Generates string representation of the object for logging purposes.
     */
    @Override
    public String toString() {
        return new StringBuilder().append("elapsedTime=").append(elapsedTime).
                append(", responseCode=").append(responseCode).toString();
    }

}
