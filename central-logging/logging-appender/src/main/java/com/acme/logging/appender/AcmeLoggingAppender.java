package com.acme.logging.appender;

import java.util.Calendar;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;
import com.acme.logging.appender.model.ApplicationLog;

public class AcmeLoggingAppender extends AppenderBase<ILoggingEvent> {

    /**
     * url to call the log service
     */
    private String url;

    /**
     * application code
     */
    private String application;

    @Override
    protected void append(final ILoggingEvent event) {
        sendToTrackingServer(event);
    }

    public HttpEntity<ApplicationLog> getEntity(final ILoggingEvent event) {

        final ApplicationLog log = new ApplicationLog();
        log.setApplication(application);
        log.setLevel(event.getLevel().levelStr);
        log.setMessage(event.getMessage());

        if (event.getThrowableProxy() != null) {
            log.setStackTrace(event.getThrowableProxy().getMessage());
        }

        log.setCreateDate(Calendar.getInstance().getTime());

        final HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json");
        final HttpEntity<ApplicationLog> entity = new HttpEntity<ApplicationLog>(log, headers);
        return entity;

    }

    public String getUrl() {
        return url;
    }

    private boolean sendToTrackingServer(final ILoggingEvent event) {

        final RestTemplate restTemplate = new RestTemplate();
        restTemplate.exchange(url, HttpMethod.PUT, getEntity(event), String.class);
        return true;
    }

    public void setApplication(final String application) {
        this.application = application;
    }

    public void setUrl(final String url) {
        this.url = url;
    }
}
