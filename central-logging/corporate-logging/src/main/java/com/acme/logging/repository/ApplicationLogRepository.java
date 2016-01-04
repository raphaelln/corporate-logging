package com.acme.logging.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.acme.logging.model.ApplicationLog;

public interface ApplicationLogRepository extends MongoRepository<ApplicationLog, String> {

}
