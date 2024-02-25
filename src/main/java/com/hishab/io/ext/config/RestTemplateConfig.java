package com.hishab.io.ext.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * The type Rest template config.
 */
@Configuration
public class RestTemplateConfig {

    /**
     * The Internal api read timeout.
     */
    @Value("${role.dice.api.read-time-out}")
    int internalApiReadTimeout;

    /**
     * The Internal api connection timeout.
     */
    @Value("${role.dice.api.connection-time-out}")
    int internalApiConnectionTimeout;

    /**
     * Rest template rest template.
     *
     * @param builder the builder
     * @return the rest template
     */
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder
                .requestFactory(this::clientHttpRequestFactory)
                .build();
    }

    /**
     * Client http request factory client http request factory.
     *
     * @return the client http request factory
     */
    private ClientHttpRequestFactory clientHttpRequestFactory() {
        SimpleClientHttpRequestFactory simpleClientHttpRequestFactory = new SimpleClientHttpRequestFactory();
        simpleClientHttpRequestFactory.setReadTimeout(internalApiReadTimeout);
        simpleClientHttpRequestFactory.setConnectTimeout(internalApiConnectionTimeout);
        return simpleClientHttpRequestFactory;
    }
}