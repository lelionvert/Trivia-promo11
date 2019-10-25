package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;

public class Players {
    ArrayList<Player> listPlayer = new ArrayList();


    public void add(String playerName) {
        listPlayer.add(new Player(playerName));
        System.out.println(playerName + " was added");
        System.out.println("They are player number " + listPlayer.size());
    }

    public Player getPlayerByIndex(int index) {
        return listPlayer.get(index);
    }

    public int size() {
        return listPlayer.size();
    }
}
