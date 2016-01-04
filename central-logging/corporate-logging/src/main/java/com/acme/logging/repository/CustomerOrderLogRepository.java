package com.acme.logging.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.acme.logging.model.CustomerOrderLog;

public interface CustomerOrderLogRepository extends MongoRepository<CustomerOrderLog, String> {

}
