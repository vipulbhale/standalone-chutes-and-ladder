package com.candidate.priceline.chutesandladder.facade;

import com.candidate.priceline.chutesandladder.model.Board;
import com.candidate.priceline.chutesandladder.model.ChutesAndLadderConfig;
import com.candidate.priceline.chutesandladder.model.Game;
import com.candidate.priceline.chutesandladder.model.MoverType;
import com.candidate.priceline.chutesandladder.model.Player;
import com.candidate.priceline.chutesandladder.model.Request;
import com.candidate.priceline.chutesandladder.service.Spinner;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RequestFacade {
    private static final Logger logger = LogManager.getLogger(RequestFacade.class.getName());

    /**
     * This method takes array of players and returns the request.
     *
     * @param playerNames
     * @return
     * @throws IOException
     */
    public Request createRequest(String chutesAndLadderConfigLocation, int numberOfSquares, Spinner spinner, String... playerNames) throws IOException {
        logger.traceEntry("createRequest");
        List<Player> orderedPlayers = this.orderPlayersBasedOnTossSpin(createPlayersUsingName(playerNames), spinner);
        logger.trace("Players playing this game are :: {}", orderedPlayers);
        logger.trace("The chuteAndLadder config location is :: {}", chutesAndLadderConfigLocation);
        Map<Integer, Integer> chutesAndLadderConfig = setupChutesAndladders(chutesAndLadderConfigLocation);
        final Board board = new Board(numberOfSquares, chutesAndLadderConfig);
        Arrays.stream(board.getSquares()).forEachOrdered(sq -> {
            if (board.getChutesAndLadderMap().get(sq.getAddress()) != null) {
                sq.setChuteOrLadder(true);
                MoverType moverType = (sq.getAddress() > board.getChutesAndLadderMap().get(sq.getAddress())) ? MoverType.CHUTE : MoverType.LADDER;
                sq.setMoverType(moverType);
            }
        });
        Game game = new Game(board, orderedPlayers);
        return new Request(game);
    }

    /**
     * Create a list of players using array of names
     *
     * @param playerNames
     * @return List<Player>
     */
    protected List<Player> createPlayersUsingName(String... playerNames) {
        logger.traceEntry("createPlayersUsingName");
        List<Player> players = Arrays.stream(playerNames)
                .map(name -> new Player(name)).collect(Collectors.toList());
        logger.traceExit("CreatePlayersUsingName");
        return players;
    }

    /**
     * Firstly spins for every player and based on the value of the spin players are ordered from highest score
     * to lowest score. Just similar to the soccer or cricket toss. This will decide who is going to play first.
     */
    protected List<Player> orderPlayersBasedOnTossSpin(List<Player> players, Spinner spinner) {
        logger.traceEntry("orderBasedOnTossSpin");
        List<Player> sortedPlayerByFirstSpin = players.stream()
                .map(player -> {
                    player.setTurnOrder(spinner.spin());
                    player.setCurrentPosition(0);
                    player.getSquarePositions().add(0);
                    return player;
                })
                .sorted(Comparator.comparingInt(Player::getTurnOrder).reversed())
                .collect(Collectors.toList());
        logger.trace("After the toss turn the order of players is :: {} ", sortedPlayerByFirstSpin);
        logger.traceExit("orderBasedOnTossSpin");
        return IntStream.range(0, sortedPlayerByFirstSpin.size())
                .mapToObj(i -> {
                    sortedPlayerByFirstSpin.get(i).setTurnOrder(i + 1);
                    return sortedPlayerByFirstSpin.get(i);
                }).collect(Collectors.toList());
    }


    /***
     * This method sets up chutes and ladders associated to the board on a game.
     * @param configPath
     * @return Map<Integer, Integer> Hashmap of StartSquare to EndSquare for Chute or Ladder
     * @throws IOException
     */
    protected Map<Integer, Integer> setupChutesAndladders(String configPath) throws IOException {
        logger.traceEntry("setupChutesAndladders");
        ObjectMapper objectMapper = new ObjectMapper();
        List<ChutesAndLadderConfig> chutesAndLadderConfigs = null;
        try {
            chutesAndLadderConfigs = objectMapper.readValue(this.getClass().getClassLoader().getResourceAsStream(configPath), new TypeReference<List<ChutesAndLadderConfig>>() {
            });
        } catch (IOException e) {
            logger.fatal("Error while configuring board and creating it with chutes and ladder config.");
            throw new IOException("Unable to read the chutes and ladder config json at "+configPath);
        }
        int randomConfigIndex = new Random().nextInt((chutesAndLadderConfigs.size()));
        logger.trace("Random chutes and ladder configuration index from repo of configuration is :: {}", randomConfigIndex);
        ChutesAndLadderConfig chutesAndLadderConfig = chutesAndLadderConfigs.get(randomConfigIndex);
        logger.trace("Random chutes and ladder configuration is :: {}", chutesAndLadderConfig);
        Map<Integer, Integer> chutesAndLadderSqConfig = chutesAndLadderConfig.getConfiguration().stream().collect(Collectors.toMap(
                chuteAndLadder -> chuteAndLadder.getStartSquare(),
                chuteAndLadder -> chuteAndLadder.getEndSquare()
        ));
        logger.trace("Random chutes and ladder configuration converting to map :: {}", chutesAndLadderSqConfig);
        return chutesAndLadderSqConfig;

    }

}


