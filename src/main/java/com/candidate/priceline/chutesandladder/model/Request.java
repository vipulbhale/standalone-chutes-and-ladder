package com.candidate.priceline.chutesandladder.model;

import java.io.Serializable;

public class Request implements Serializable {

    private static final long serialVersionUID = 4502923965126546045L;
    private Game game;

    public Request() {
    }

    public Request(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }


    @Override
    public String toString() {
        return "Request{" +
                "game=" + game +
                '}';
    }
}
