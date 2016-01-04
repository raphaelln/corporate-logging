package com.acme.logging.spark;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.bson.BSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.acme.logging.model.ApplicationLog;

/**
 * Spark batch to reduce and filter the logs from application and log level
 * 
 * @author rnascimento
 */
public class SparkBatch implements Serializable {

    private static final Logger LOG = LoggerFactory.getLogger(SparkBatch.class);

    private final String application;

    private final String level;

    private final String message;

    private final String stack;

    public SparkBatch(final String application, final String level, final String message,
        final String stack) {
        this.application = application;
        this.level = level;
        this.message = message;
        this.stack = stack;
    }

    /**
     * Do the filter on entire logs collection from mongodb
     * 
     * @param sc
     *            - JavaSparkContext
     * @param mongoCollection
     *            - Name of mongoCollection
     * @return
     */
    public List<ApplicationLog> getApplicationLog(final JavaSparkContext sc, final String mongoCollection) {

        LOG.debug("Extracking logs from log central with application: {} , level {} , message {} and stack {}", application,
            level, message, stack);
        final Configuration hadoopConfig = new Configuration();
        hadoopConfig.set("mongo.input.uri", mongoCollection);

        final JavaPairRDD<Object, BSONObject> mongoRDD = sc.newAPIHadoopRDD(hadoopConfig,
            com.mongodb.hadoop.MongoInputFormat.class,
            Object.class, BSONObject.class);

        JavaRDD<ApplicationLog> logs = mongoRDD.flatMap(line -> Arrays.asList(ApplicationLog.convertFromBSONObject(line._2)))
            .filter(s -> s.getApplication().equals(application))
            .filter(s -> s.getLevel().equals(level));

        if (StringUtils.isNotBlank(this.message)) {
            logs = logs.filter(s -> s.getMessage().contains(this.message));
        }

        if (StringUtils.isNotBlank(this.stack)) {
            logs = logs.filter(s -> s.getStackTrace().contains(this.stack));
        }

        return logs.collect();
    }

}
