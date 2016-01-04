package com.acme.logging.controller;

import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.acme.logging.model.ApplicationLog;
import com.acme.logging.repository.ApplicationLogRepository;
import com.acme.logging.spark.LogService;
import com.acme.logging.utils.DatabaseUtils;

/**
 * Application Log endpoint
 * 
 * @author rnascimento
 */
@RestController
@RequestMapping("/logs/application")
public class ApplicationLogController {

    @Autowired
    private ApplicationLogRepository repository;

    @Autowired
    private DatabaseUtils databaseUtils;

    @Autowired
    private LogService logService;

    @RequestMapping(method = RequestMethod.GET)
    public List<ApplicationLog> getLogs(@RequestParam(value = "application") final String applicationCode,
        @RequestParam(value = "level") final String level,
        @RequestParam(value = "message", required = false) final String message,
        @RequestParam(value = "stack", required = false) final String stack) {

        final List<ApplicationLog> appLogs = logService.getApplicationLog(applicationCode, level, message, stack);
        return appLogs;
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = "application/json")
    public String insertLog(@RequestBody final ApplicationLog log) {

        Validate.isTrue(StringUtils.isNotBlank(log.getApplication()), "Application must be informed.");
        Validate.isTrue(StringUtils.isNotBlank(log.getLevel()), "Debug level must be informed.");
        Validate.isTrue(StringUtils.isNotBlank(log.getMessage()), "Message must be informed.");

        if (log.getCreateDate() == null) {
            log.setCreateDate(Calendar.getInstance().getTime());
        }
        repository.save(log);

        return "Application log created !!!";
    }

    @RequestMapping("/mockup/database")
    public void mockupBase() {
        databaseUtils.populateApplicationLog();
    }
}
