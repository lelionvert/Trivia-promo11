package com.adaptionsoft.games.uglytrivia;

public class Game {
    Players players;
    final Deck deck;
    boolean isGettingOutOfPenaltyBox;

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

        boolean isCurrentPlayerInPenaltyBox = players.getCurrentPlayer().isInPenalty();

        if (isCurrentPlayerInPenaltyBox && isEven(roll)) {
            // Extract in method enterPenaltyBox
            isGettingOutOfPenaltyBox = false;
            System.out.println(players.getCurrentPlayer() + " is not getting out of the penalty box");
            return;
        }

        if (isCurrentPlayerInPenaltyBox && isOdd(roll)) {
            // Extract in method exitPenaltyBox
            isGettingOutOfPenaltyBox = true;
            System.out.println(players.getCurrentPlayer() + " is getting out of the penalty box");
        }

        players.getCurrentPlayer().move(roll);
        currentCategory().print();
        deck.printQuestionCategory(currentCategory());
    }

    private boolean isOdd(int roll) {
        return roll % 2 != 0;
    }

    private boolean isEven(int roll) {
        return roll % 2 == 0;
    }

    private QuestionCategory currentCategory() {
        return QuestionCategory.getCategoryBy(players.getCurrentPlayer().getPlace());
    }

    public boolean wasCorrectlyAnswered() { // plusieurs responsabilites? renommer?
        if (players.getCurrentPlayer().isInPenalty() && !isGettingOutOfPenaltyBox) {
            return true;
        }

        if (players.getCurrentPlayer().isInPenalty() && isGettingOutOfPenaltyBox) {
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
