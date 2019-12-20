package com.candidate.priceline.chutesandladder.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.Properties;

public class ApplicationProperties {
    private static final Logger logger = LogManager.getLogger(ApplicationProperties.class.getName());

    private static ApplicationProperties applicationProperties;
    private final Properties properties = new Properties();

    public static ApplicationProperties getInstance() {
        if (applicationProperties == null) {
            applicationProperties = new ApplicationProperties();
        }
        return applicationProperties;
    }

    private ApplicationProperties() {
        logger.traceEntry("ApplicationProperties");
        Optional<String> optionalEnvironmentName = Optional.ofNullable(System.getProperties().getProperty("environment"));
        StringBuilder environ = new StringBuilder("application.properties");
        if (optionalEnvironmentName.isPresent()){
                        environ.replace( 11,12, (".").concat(optionalEnvironmentName.get()).concat("."));
        }
        String environmentPropertiesFile = environ.toString();
        logger.trace("Location of environment properties file :: {}", environmentPropertiesFile);
        try {
            InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(environmentPropertiesFile);
            properties.load(inputStream);
        } catch (IOException e) {
            logger.fatal("Exception while loading properties file for given environment :: {} :: exception {}", optionalEnvironmentName.orElse(""), e);
            System.exit(1);
        }
        logger.traceExit("ApplicationProperties");
    }

    public Properties getProperties() {
        return properties;
    }

    public String getProperty(String propertyName) {
        return this.getProperties().getProperty(propertyName);
    }

}
