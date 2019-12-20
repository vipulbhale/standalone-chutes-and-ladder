package com.candidate.priceline.chutesandladder.model;

import java.io.Serializable;
import java.util.List;

/**
 * This is the central part of the ChutesAndLadder. Every instance of this class depicts a Game.
 */
public class Game implements Serializable {
    private static final long serialVersionUID = 1733911894968454362L;

    private long id;
    private final Board board;
    private final List<Player> players;

    public Game(Board board, List<Player> players) {
        this.board = board;
        this.players = players;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Board getBoard() {
        return board;
    }

    public List<Player> getPlayers() {
        return players;
    }

    @Override
    public String toString() {
        return "Game{" +
                "board=" + board +
                ", players=" + players +
                '}';
    }
}
