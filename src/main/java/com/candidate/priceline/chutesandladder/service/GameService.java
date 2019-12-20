package com.candidate.priceline.chutesandladder.service;

import com.candidate.priceline.chutesandladder.model.MoverType;
import com.candidate.priceline.chutesandladder.model.Request;
import com.candidate.priceline.chutesandladder.model.Player;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Map;

public class GameService {
    private static final Logger logger = LogManager.getLogger(GameService.class.getName());

    /**
     * Methdd starts the game.
     *
     * @return String : name of the winner
     */
    public String startGame(Request request, final Spinner spinner) {
        logger.traceEntry("startGame");
        Map<Integer, Integer> chuteAndLadderConfig = request.getGame().getBoard().getChutesAndLadderMap();
        Player player = null;
        int turn = 0;
        List<Player> players = request.getGame().getPlayers();
        while (true) {
            for (int i = 0; i < players.size(); i++) {
                player = players.get(i);
                int currentPosition = player.getCurrentPosition();
                logger.trace("Spinning the wheel");
                int spin = spinner.spin();
                turn++;
                logger.trace("Spin value for this turn{},is {}", turn,spin);
                int newPosition = currentPosition + spin;
                int effectiveNewPosition = newPosition;
                if (newPosition <= 100) {
                    if (request.getGame().getBoard().getSquares()[currentPosition + spin].hasChuteOrLadder()) {
                        effectiveNewPosition = chuteAndLadderConfig.get(currentPosition + spin);
                        System.out.println(printOutput(turn, player.getName(), currentPosition, newPosition, request.getGame().getBoard().getSquares()[currentPosition + spin].getMoverType(), effectiveNewPosition));
                    } else {
                        System.out.println(printOutput(turn, player.getName(), currentPosition, effectiveNewPosition));
                    }
                    logger.trace("New effective position is {}",effectiveNewPosition);
                    player.setCurrentPosition(effectiveNewPosition);
                    player.getSquarePositions().add(effectiveNewPosition);
                    if (effectiveNewPosition == 100){
                        logger.trace("Player reached at square 100 {}",player.getName());
                        logger.trace("Players all attributes are {}", player);
                        logger.traceExit("startGame");
                        return player.getName();
                    }
                }
            }
        }
    }

    /**
     * This is output format
     *
     * @param turn            total number of turns in the game
     * @param playerName      Name of the player
     * @param currentPosition currentPosition of the player pawn on the board
     * @param nextPosition    effective nextPosition of the player after spinner is run on the board
     */
    protected String printOutput(int turn, String playerName, int currentPosition, int nextPosition) {
        return String.format("%d: %s: %d --> %d", turn, playerName, currentPosition, nextPosition);
    }

    /**
     * @param turn
     * @param playerName
     * @param currentPosition
     * @param nextPosition
     * @param chuteOfLadder
     * @param effectiveNewPosition
     */
    protected String printOutput(int turn, String playerName, int currentPosition, int nextPosition, MoverType chuteOfLadder, int effectiveNewPosition) {
        return String.format("%d: %s: %d --> %d --%s--> %d", turn, playerName, currentPosition, nextPosition, chuteOfLadder, effectiveNewPosition);

    }



}
