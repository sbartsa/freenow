package com.freenow.test.helper;

import org.testng.annotations.BeforeSuite;

/**
 * Created by stevevarsanis on 27/07/19.
 */
public class CoreHelper {

    private static String LINE = "-------------------------------------------------------------------------";
    public static Core core;
    public static Logger logger;
    public static ConfigProperties properties;

    @BeforeSuite(alwaysRun = true, groups = "initial")
    public Core Initialize() {

        try {
            core = new Core();
            logger = new Logger();
            properties = new ConfigProperties();
            logger.info("Core initialized");
            printConfiguration();
            } catch (Exception e) {
            logger.error("Problem initializing core");
                e.printStackTrace();
        }
        return core;
    }

    private void printConfiguration() {
        if(properties.getPropertyValue(ConfigProperties.PRINT_CONFIGURATION_ON_STARTUP).equals("true")) {
            logger.info(LINE);
            logger.info("Configuration filename             : " + ConfigProperties.configurationFileName);
            logger.info("Configuration path                 : " + ConfigProperties.configurationsFilePath);
            logger.info("Application URL                    : " + (properties.getPropertyValue(ConfigProperties.APPLICATION_URL)));
            logger.info(LINE);
        }
    }
}
