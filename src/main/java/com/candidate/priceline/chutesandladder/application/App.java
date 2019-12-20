package com.candidate.priceline.chutesandladder.application;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This is the application class. This is the entrypoint for Chutes and ladder game.
 * This class parses the arguments that is the name of the players , instatiates the Driver class and run it in a thread
 */
public class App {
    private static final Logger logger = LogManager.getLogger(App.class.getName());

    /**
     * Entry point for the application.
     *
     * @param args these are names of the players.
     */
    public static void main(String[] args) {
        logger.info("Starting the application");
        new Thread(new Driver(args)).start();
        logger.info("Exiting the application.");
    }


}
