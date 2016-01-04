package com.acme.logging.spark;

import java.util.List;

import com.acme.logging.model.ApplicationLog;

public interface LogService {

    /**
     * Retrieves logs from database by application and level and not required message and stack
     * 
     * @param application
     *            - Name of application
     * @param level
     *            - Level
     * @param message
     *            - Logs with contais message
     * @param stack
     *            - Logs with contains stack
     * @return
     */
    public List<ApplicationLog> getApplicationLog(final String application, final String level, final String message,
        final String stack);

}
