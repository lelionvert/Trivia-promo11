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

    public void move(int roll) {
        place = (place + roll) % 12;
        System.out.println(name + "'s new location is " + place);
    }

    @Override
    public String toString() {
        return name;
    }

    public int getPlace() {
        return place;
    }

    public void increasePurse() {
        purse++;
        System.out.println(name + " now has " + purse + " Gold Coins.");
    }

    public int getPurse() {
        return purse;
    }

    public boolean isInPenalty() {
        return inPenalty;
    }

    public void setInPenalty(boolean bool) {
        inPenalty = bool;
    }

    public void print() {
        System.out.println(name + " is the current player");
    }

}
