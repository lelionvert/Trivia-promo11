package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;

public class Players {
    ArrayList<Player> players = new ArrayList();
    private int currentPlayer = 0;

    public void add(String playerName) {
        players.add(new Player(playerName));
        System.out.println(playerName + " was added");
        System.out.println("They are player number " + players.size());
    }

    public Player getCurrentPlayer() {
        return players.get(currentPlayer);
    }

    public void nextPlayer() {
        currentPlayer++;
        if (currentPlayer == players.size()) {
            currentPlayer = 0;
        }
    }
}
