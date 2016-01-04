package com.acme.logging.controller;

import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.acme.logging.model.CustomerOrderLog;
import com.acme.logging.repository.CustomerOrderLogRepository;
import com.acme.logging.utils.DatabaseUtils;

/**
 * Log Customer endpoint
 * 
 * @author rnascimento
 */
@RestController
@RequestMapping("/logs/customer")
public class CustomerOrderLogController {

    @Autowired
    private CustomerOrderLogRepository repository;

    @Autowired
    private DatabaseUtils databaseUtils;

    @RequestMapping(method = RequestMethod.PUT, consumes = "application/json")
    public String insertLog(@RequestBody final CustomerOrderLog log) {

        Validate.isTrue((log.getProductId() != null && (log.getCustomerId().intValue() > 0)), "Product Id must be informed.");
        Validate.isTrue((log.getCustomerId() != null && (log.getCustomerId().intValue() > 0)), "Customer Id must be informed.");

        repository.save(log);

        return "Customer log created !!!";
    }

}
