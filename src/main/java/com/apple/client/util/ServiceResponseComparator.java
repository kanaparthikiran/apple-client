/*
 * Copyright 2019 Apple. All Rights Reserved.
 *
 * This software is the proprietary information of Apple.
 * Use is subject to license terms.
 */
package com.apple.client.util;

import java.util.Comparator;

import com.apple.client.bean.ServiceResponse;

/**
 * Comparator used to compare and sort Response Objects
 * based on the response time.
 *
 * @author Kiran Kanaparthi
 * @param <T> Type of the Object undert comparison.
 * @since May 03, 2019
 */
public class ServiceResponseComparator<T> implements Comparator<ServiceResponse> {

    @Override
    public int compare(ServiceResponse o1, ServiceResponse o2) {
        if (o1 != null && o2 != null) {
            return (int) (o1.getElapsedTime() - o2.getElapsedTime());
        }
        return 0;
    }

}
