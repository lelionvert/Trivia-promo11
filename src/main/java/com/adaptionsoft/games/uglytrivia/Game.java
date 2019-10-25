package com.adaptionsoft.games.uglytrivia;

public class Game {
    Players players;
    final Deck deck;

    public Game() {
        deck = Deck.initializeDeck();
        players = new Players();
    }

    // Change signature, name, one responsability thing
    public void add(String playerName) {
        players.add(playerName);
    }

    public void roll(int roll) {
        players.getCurrentPlayer().print();

        System.out.println("They have rolled a " + roll); // faute orthographe --> pour plus tard

        if (players.getCurrentPlayer().tryExitPenaltyBox(roll)) return;

        players.getCurrentPlayer().move(roll);
        currentCategory().print();
        deck.printQuestionCategory(currentCategory());
    }

    private QuestionCategory currentCategory() {
        return QuestionCategory.getCategoryBy(players.getCurrentPlayer().getPlace());
    }

    public boolean wasCorrectlyAnswered() { // plusieurs responsabilites? renommer?
        if (players.getCurrentPlayer().isInPenalty() && !players.getCurrentPlayer().isGettingOut()) {
            return true;
        }


        if (players.getCurrentPlayer().isInPenalty() && players.getCurrentPlayer().isGettingOut()) {
            System.out.println("Answer was correct!!!!");
        } else {
            System.out.println("Answer was corrent!!!!"); // faute orthographe --> pour plus tard
        }

        players.getCurrentPlayer().increasePurse();
        return didPlayerWin();
    }

    private boolean didPlayerWin() {
        return players.getCurrentPlayer().getPurse() != 6;
    }

    public void nextPlayer() {
        players.nextPlayer();
    }

    public boolean wrongAnswer() { // return always true
        System.out.println("Question was incorrectly answered");
        System.out.println(players.getCurrentPlayer() + " was sent to the penalty box");
        players.getCurrentPlayer().setInPenalty(true);
        nextPlayer();
        return true;
    }

}
