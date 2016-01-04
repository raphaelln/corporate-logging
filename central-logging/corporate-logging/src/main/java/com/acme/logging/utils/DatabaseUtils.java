package com.acme.logging.utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.acme.logging.model.ApplicationLog;
import com.acme.logging.repository.ApplicationLogRepository;

/**
 * Initial data
 * 
 * @author rnascimento
 */
@Component
public class DatabaseUtils {

    @Autowired
    private ApplicationLogRepository repository;

    public void populateApplicationLog() {

        final List<String> applications = new ArrayList<String>();
        applications.add("musicshop");
        applications.add("bookshop");
        applications.add("shoeshop");
        applications.add("financial");
        applications.add("eletronicshop");

        final List<String> debugLevel = new ArrayList<String>();
        debugLevel.add("DEBUG");
        debugLevel.add("INFO");
        debugLevel.add("WARNING");
        debugLevel.add("ERROR");

        final List<String> stackTrace = new ArrayList<String>();
        stackTrace.add("NullpointerException");
        stackTrace.add("");
        stackTrace.add("IllegalArgumentException");
        stackTrace.add("BussinessException");

        final List<String> message = new ArrayList<String>();
        message.add("Error to add item xx on cart");
        message.add("Error to retrieve user account information.");
        message.add("Error to confirm your credid card");
        message.add("Credit card information is invalid");

        final Random random = new Random();
        for (int i = 0; i < 1000000; i++) {
            final ApplicationLog log = new ApplicationLog();
            log.setApplication(applications.get(random.nextInt(applications.size())));
            log.setLevel(debugLevel.get(random.nextInt(debugLevel.size())));
            log.setStackTrace(stackTrace.get(random.nextInt(stackTrace.size())));
            log.setMessage(message.get(random.nextInt(message.size())));
            log.setCreateDate(Calendar.getInstance().getTime());
            repository.save(log);
        }
    }

}
