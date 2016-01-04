# corporate-logging

This is a small application to ilustrate a idea of a central log application for a company with a lot of business applications.

This application uses:

corporate-logging:
- mongodb as centralized repository using spring-data and spring-mongodb adapter.
- spark as search engine with sparks jobs to search words in all logs of the corporation.
- spring-mvc: some endpoints to search and input logs.

logging-appender:
 - a simple appender for logback, which will automatically insert the log in the mongodb using the corporate-logging endpoints.
