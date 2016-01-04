package com.acme.logging.appender.model;

import java.io.Serializable;
import java.util.Date;

public class ApplicationLog implements Serializable {

    private String id;

    private String application;

    private String level;

    private String message;

    private String stackTrace;

    private Date createDate;

    public String getApplication() {
        return application;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public String getId() {
        return id;
    }

    public String getLevel() {
        return level;
    }

    public String getMessage() {
        return message;
    }

    public String getStackTrace() {
        return stackTrace;
    }

    public void setApplication(final String applicationCode) {
        this.application = applicationCode;
    }

    public void setCreateDate(final Date createDate) {
        this.createDate = createDate;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public void setLevel(final String level) {
        this.level = level;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public void setStackTrace(final String stackTrace) {
        this.stackTrace = stackTrace;
    }

}
