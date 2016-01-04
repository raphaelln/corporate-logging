package com.acme.logging.spark;

import java.util.List;

import org.apache.spark.api.java.JavaSparkContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acme.logging.configuration.MongoConfiguration;
import com.acme.logging.model.ApplicationLog;

@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private JavaSparkContext sc;

    @Autowired
    private MongoConfiguration mongoConfig;

    public List<ApplicationLog> getApplicationLog(final String application, final String level, final String message,
        final String stack) {
        final SparkBatch batch = new SparkBatch(application, level, message, stack);
        return batch.getApplicationLog(sc, mongoConfig.getUri() + "." + mongoConfig.getAppCollection());
    }

}
