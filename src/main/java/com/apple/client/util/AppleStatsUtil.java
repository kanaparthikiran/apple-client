/*
 * Copyright 2019 Apple. All Rights Reserved.
 *
 * This software is the proprietary information of Apple.
 * Use is subject to license terms.
 */
package com.apple.client.util;

import java.util.List;
import org.springframework.util.CollectionUtils;

import com.apple.client.bean.ServiceResponse;
import com.apple.client.exception.AppleClientException;

/**
 * Utility class for Apple Http Client that
 * provides utilities for calculating mean,standard deviation, and
 * percentiles.
 *
 * @author Kiran Kanaparthi
 * @since May 03, 2019
 */
public final class AppleStatsUtil {

    /**
     * Utility class throwing Exception when tried to instantiate.
     *
     * @throws AppleClientException Application Client exception.
     */
    private AppleStatsUtil() throws AppleClientException {
        throw new AppleClientException("Util class, " +
            " not meant for instantiation");
    }

    /**
     * Calculates the Standard Deviation of the data.
     *
     * @param data The given list of values.
     * @param mean mean of the given list of values.
     * @return the standard deviation of the given list of values.
     */
    public static double getStandardDeviation(List<ServiceResponse> data, double mean) {
        double standardDeviation = 0;
        double numerator = 0;
        if (!CollectionUtils.isEmpty(data)) {
            for (ServiceResponse value : data) {
                numerator += (value.getElapsedTime() - mean) *
                     (value.getElapsedTime() - mean);
            }
            standardDeviation = Math.sqrt(numerator / (data.size() - 1));
        }
        return standardDeviation;
    }

    /**
     * Calculates the Mean of the supplied values.
     *
     * @param data list of values.
     * @return mean of the data.
     */
    public static double getMean(List<ServiceResponse> data) {
        double mean = 0;
        if (!CollectionUtils.isEmpty(data)) {
            mean = (data.stream().mapToDouble(
                x->x.getElapsedTime()).sum()) / data.size();
        }
        return mean;
    }

    /**
     * Calculates the percentile of all the values.
     *
     * @param data list of values for percentile calculation.
     * @param percentile required percentiles for the data.
     * @return Required The precentile of the data.
     */
    public static long getPercentile(List<ServiceResponse> data, double percentile) {
        double size = 0;
        long result = 0;
        if (!CollectionUtils.isEmpty(data)) {
            size = data.size();
            int percentileRecord = (int) Math.floor(size * (percentile / 100));
            //check if the index falls within the index for percentile
            if (percentileRecord <= size - 1) {
                result = data.get(percentileRecord).getElapsedTime();
            }
        }
        return result;
    }
}
