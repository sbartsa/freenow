package com.freenow.test.helper;

/**
 * Created by stevevarsanis on 27/07/19.
 */
public class Management {

    public Logger   logger;
    public ConfigProperties properties;


    public Management() {
        this.properties = new ConfigProperties();
        this.logger = new Logger();
    }
}
