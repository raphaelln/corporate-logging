package com.acme.logging.configuration;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Spark configuration propperties
 * 
 * @author rnascimento
 */
@Configuration
@ConfigurationProperties(prefix = "spark")
public class SparkConfig {

    private static final Logger LOG = LoggerFactory.getLogger(SparkConfig.class);

    /**
     * spark host
     */
    private String master;

    /**
     * memory to use
     */
    private String memory;

    /**
     * cores to execute spark batchs
     */
    private String core;

    /**
     * number of parelism
     */
    private String paralelism;

    private String hadoopHomeDir;

    public String getCore() {
        return core;
    }

    public String getHadoopHomeDir() {
        return hadoopHomeDir;
    }

    public String getMaster() {
        return master;
    }

    public String getMemory() {
        return memory;
    }

    public String getParalelism() {
        return paralelism;
    }

    @Bean
    public JavaSparkContext javaSparkContext()
    {
        System.setProperty("hadoop.home.dir", hadoopHomeDir);

        LOG.info("Creating SparkContext.  Master=" + master);
        final SparkConf conf = new SparkConf().setAppName("SparkForSpring")
            .setMaster(master)
            .set("spark.executor.memory", memory)
            .set("spark.cores.max", core)
            .set("spark.default.parallelism", paralelism);

        return new JavaSparkContext(conf);
    }

    public void setCore(final String core) {
        this.core = core;
    }

    public void setHadoopHomeDir(final String hadoopHomeDir) {
        this.hadoopHomeDir = hadoopHomeDir;
    }

    public void setMaster(final String master) {
        this.master = master;
    }

    public void setMemory(final String memory) {
        this.memory = memory;
    }

    public void setParalelism(final String paralelism) {
        this.paralelism = paralelism;
    }
}
