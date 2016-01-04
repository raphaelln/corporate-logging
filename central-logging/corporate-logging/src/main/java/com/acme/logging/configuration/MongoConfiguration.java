package com.acme.logging.configuration;

import java.io.Serializable;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Binding class to mongodb configurations
 * 
 * @author rnascimento
 */
@Configuration
@ConfigurationProperties(prefix = "spring.data.mongodb")
public class MongoConfiguration implements Serializable {

    /**
     * host connection mongodb instance
     */
    private String host;

    /**
     * port connection mongodb instance
     */
    private int port;

    /**
     * complete uri connection with mongodb
     */
    private String uri;

    /**
     * Collection name
     */
    private String appCollection;

    /**
     * Collection name
     */
    private String customerCollection;

    public String getAppCollection() {
        return appCollection;
    }

    public String getCustomerCollection() {
        return customerCollection;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public String getUri() {
        return uri;
    }

    public void setAppCollection(final String collection) {
        this.appCollection = collection;
    }

    public void setCustomerCollection(final String customerCollection) {
        this.customerCollection = customerCollection;
    }

    public void setHost(final String host) {
        this.host = host;
    }

    public void setPort(final int port) {
        this.port = port;
    }

    public void setUri(final String uri) {
        this.uri = uri;
    }

}
