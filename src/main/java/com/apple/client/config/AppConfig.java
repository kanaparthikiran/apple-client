/*
 * Copyright 2019 Apple. All Rights Reserved.
 *
 * This software is the proprietary information of Apple.
 * Use is subject to license terms.
 */
package com.apple.client.config;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
/**
 * Provides Application Configuration, and creates restTemplate bean for use
 * in the application.
 *
 * @author Kiran Kanaparthi
 * @since May 03, 2019
 */
@Configuration
public class AppConfig {

    @Autowired
    private CloseableHttpClient httpClient;

    /**
     * Creates the RestTemplate with the given HTTP Request Factory and HTTP Client.
     *
     * @return  restTemplate with the given HTTPClient and SSL configuration.
     * @throws KeyStoreException thrown If the Keystore is not found.
     * @throws NoSuchAlgorithmException thrown If there is no such Algorithm exists.
     * @throws KeyManagementException throws If there is an exception with Key Management.
     */
    @Bean
    public RestTemplate restTemplate() throws KeyStoreException,
        NoSuchAlgorithmException, KeyManagementException {
        HttpComponentsClientHttpRequestFactory requestFactory =
                        new HttpComponentsClientHttpRequestFactory();
        requestFactory.setHttpClient(httpClient);
        return  new RestTemplate(requestFactory);
    }
}
