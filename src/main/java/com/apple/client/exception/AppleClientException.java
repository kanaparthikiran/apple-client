/*
 * Copyright 2019 Apple. All Rights Reserved.
 *
 * This software is the proprietary information of Apple.
 * Use is subject to license terms.
 */
package com.apple.client.exception;
/**
 * Custom Checked Exception for Apple Http Client
 *
 * @author Kiran Kanaparthi
 * @since May 03, 2019
 */
public class AppleClientException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 7672041431905479770L;

    /**
     * Create a new {@code AppleClientException}
     * with the specified detail message and no root cause.
     * @param msg the detail message
     */
    public AppleClientException(String msg) {
        super(msg);
    }

    /**
     * Create a new {@code AppleClientException}
     * with the specified detail message and the given root cause.
     * @param msg the detail message
     * @param cause the root cause
     */
    public AppleClientException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
