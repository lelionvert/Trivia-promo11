package com.adaptionsoft.games.uglytrivia;

public class Player {

    private String name;
    private int place;
    private int purse;
    private boolean inPenalty;

    public Player(String name) {
        this.name = name;
        this.place = 0;
        this.purse = 0;
        this.inPenalty = false;
    }

}
