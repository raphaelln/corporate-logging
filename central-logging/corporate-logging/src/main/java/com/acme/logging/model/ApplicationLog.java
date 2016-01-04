package com.acme.logging.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.bson.BSONObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "application_log")
public class ApplicationLog implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public static ApplicationLog convertFromBSONObject(final BSONObject bsonObject) {

        final ApplicationLog app = new ApplicationLog();
        app.setId(bsonObject.get("_id").toString());
        app.setApplication(bsonObject.get("application") != null ? (String) bsonObject.get("application") : StringUtils.EMPTY);
        app.setLevel(bsonObject.get("level") != null ? (String) bsonObject.get("level") : StringUtils.EMPTY);
        app.setMessage(bsonObject.get("message") != null ? (String) bsonObject.get("message") : StringUtils.EMPTY);
        app.setStackTrace(bsonObject.get("stackTrace") != null ? (String) bsonObject.get("stackTrace") : StringUtils.EMPTY);

        if (bsonObject.get("createDate") != null) {
            app.setCreateDate((Date) bsonObject.get("createDate"));
        }
        return app;
    }

    @Id
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
