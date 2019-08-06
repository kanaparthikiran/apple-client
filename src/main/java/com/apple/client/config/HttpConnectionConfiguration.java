/*
 * Copyright 2019 Apple. All Rights Reserved.
 *
 * This software is the proprietary information of Apple.
 * Use is subject to license terms.
 */
package com.apple.client.config;

import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import javax.net.ssl.SSLContext;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.StandardHttpRequestRetryHandler;
/**
 * Client Http Pool Configuration for CloseableHttpClient.
 * It configures the Http Client Connection TimeOut, PoolSize,
 * Connection Timeout, Socket TimeOut. Reads all the values from
 * the configuration file.
 *
 * @author Kiran Kanaparthi
 * @since May 03, 2019
 */
@Configuration
public class HttpConnectionConfiguration {

    @Autowired
    private ClientConfiguration poolConfiguration;


    /**
     * Creates HttpClientConnectionManager with the Http Connection Pool Configuration.
     *
     * @return HTTP Client Connection Manager.
     */
    @Bean
    public PoolingHttpClientConnectionManager poolingHttpClientConnectionManager() {
        PoolingHttpClientConnectionManager result = new PoolingHttpClientConnectionManager();
        result.setDefaultMaxPerRoute(poolConfiguration.getMaxPerRoute());
        result.setMaxTotal(poolConfiguration.getMaxTotal());
        return result;
    }

    /**
     * Builds HTTP Request Configuration with the configuration.
     *
     * @return HTTP Request Configuration.
     */
    @Bean
    public RequestConfig requestConfig() {
        return RequestConfig.custom()
                .setConnectionRequestTimeout(poolConfiguration.getConnectionRequestTimeout())
                .setConnectTimeout(poolConfiguration.getConnectTimeout())
                .setSocketTimeout(poolConfiguration.getSocketTimeout())
                .build();
    }

   /**
    * Builds CloseableHTTPClient with the given Connection Manager and Http Request Configuration.
    *
    * @param poolingHttpClientConnectionManager HTTP Client Connection Pooling Manager.
    * @param requestConfig Http Request Configuration.
    * @return returns ClosebleHttpClient.
    * @throws KeyStoreException KeyStore Exception.
    * @throws NoSuchAlgorithmException Throws if there is no such Algorithm available.
    * @throws KeyManagementException throws if any issues with the Key.
    */
    @Bean
    public CloseableHttpClient httpClient(PoolingHttpClientConnectionManager poolingHttpClientConnectionManager,
          RequestConfig requestConfig) throws
              KeyManagementException, NoSuchAlgorithmException, KeyStoreException {
        TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;
        SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom()
              .loadTrustMaterial(null, acceptingTrustStrategy)
              .build();
        SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);
        HttpRequestRetryHandler retryHandler = new StandardHttpRequestRetryHandler(
            poolConfiguration.getRetryCount(), poolConfiguration.isRetryEnabled());
        return HttpClients.custom()
              .setSSLSocketFactory(csf).setRetryHandler(retryHandler)
              .build();
    }
}

