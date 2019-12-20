package com.candidate.priceline.chutesandladder.application;

import com.candidate.priceline.chutesandladder.exception.InvalidInputException;
import com.candidate.priceline.chutesandladder.facade.RequestFacade;
import com.candidate.priceline.chutesandladder.model.Request;
import com.candidate.priceline.chutesandladder.service.GameService;
import com.candidate.priceline.chutesandladder.service.Spinner;
import com.candidate.priceline.chutesandladder.util.ApplicationProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Arrays;

public class Driver implements Runnable {
    private static final Logger logger = LogManager.getLogger(Driver.class.getName());

    private final String[] args;

    public Driver(String... args) {
        this.args = args;
    }

    /**
     *
     * @param requestFacade
     * @param gameService
     * @param spinner
     * @param playerNames
     * @return String Winner Players name
     */
    protected String run(RequestFacade requestFacade, GameService gameService, Spinner spinner, String... playerNames) {
        logger.traceEntry("run with parameters");

        try {
            argProcessor(args);
        } catch (InvalidInputException e) {
            logger.fatal("Error while processing the arguments. Exiting");
            System.exit(1);
        }

        int numberOfSquares = Integer.parseInt(ApplicationProperties.getInstance().getProperty("square.total"));
        String chutesAndLadderConfigLocation = ApplicationProperties.getInstance().getProperty("location.chutesandladder");

        Request request = null;
        try {
            request = requestFacade.createRequest(chutesAndLadderConfigLocation, numberOfSquares, spinner, playerNames);
        } catch (IOException e) {
            logger.fatal("Error while processing the arguments. Exiting");
            System.exit(1);
        }
        logger.info("Created Request is {}", request);
        logger.traceExit("run with parameters");
        return String.format("The winner is %s!", gameService.startGame(request, spinner));
    }

    /**
     * Overriden Run method of Runnable
     */
    @Override
    public void run() {
        logger.traceEntry("run");
        String winnerPlayerName = run(new RequestFacade(), new GameService(), Spinner.getInstance(), this.args);
        System.out.println(winnerPlayerName);
        logger.traceExit("run");

    }

    /**
     * @param args
     * @return args(Array of string)
     * @throws InvalidInputException
     */
    protected void argProcessor(String... args) throws InvalidInputException {
        logger.traceEntry("argProcessor");
        if (args == null) {
            logger.error("Arguments provided to application is null.");
            throw new InvalidInputException("No Arguments are provided");
        }
        if (args.length < 2 || args.length > 4) {
            logger.error("Number of args is less than 2 or greater than 4");
            throw new InvalidInputException("Number of players is either less than 2 or greater than 4. Please provide more than 4 and less than 2 player names.");
        }
        if (!Arrays.stream(args)
                .noneMatch(player -> player == null || player.trim().isEmpty())) {
            logger.error("One of the player name is empty");
            throw new InvalidInputException("One of the player names is empty. Please provide valid player names.");
        }
        logger.traceExit("argProcessor");
    }
}
