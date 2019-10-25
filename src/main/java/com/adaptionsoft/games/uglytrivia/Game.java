package com.adaptionsoft.games.uglytrivia;

public class Game {
    Players players;
    final Deck deck;
    int currentPlayer = 0;
    boolean isGettingOutOfPenaltyBox;

    public Game() {
        deck = Deck.initializeDeck();
        players = new Players();
    }

    // Not used
    public boolean isPlayable() {
        return (howManyPlayers() >= 2);
    }

    // Change signature, name, one responsability thing
    public void add(String playerName) {
        players.add(playerName);
    }

    private Player getCurrentPlayer() {
        return players.getPlayerByIndex(currentPlayer);
    }

    public int howManyPlayers() {
        return players.size();
    }

    public void roll(int roll) {
        System.out.println(getCurrentPlayer() + " is the current player");
        System.out.println("They have rolled a " + roll);//faute orthographe --> pour plus tard

        boolean isCurrentPlayerInPenaltyBox = getCurrentPlayer().isInPenalty();

        if (isCurrentPlayerInPenaltyBox && isEven(roll)) {
            // Extract in method enterPenaltyBox
            isGettingOutOfPenaltyBox = false;
            System.out.println(getCurrentPlayer() + " is not getting out of the penalty box");
            return;
        }

        if (isCurrentPlayerInPenaltyBox && isOdd(roll)) {
            // Extract in method exitPenaltyBox
            isGettingOutOfPenaltyBox = true;
            System.out.println(getCurrentPlayer() + " is getting out of the penalty box");
        }

        getCurrentPlayer().move(roll);
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
        return QuestionCategory.getCategoryBy(getCurrentPlayer().getPlace());
    }

    public boolean wasCorrectlyAnswered() { // plusieurs responsabilites? renommer?
        if (getCurrentPlayer().isInPenalty() && !isGettingOutOfPenaltyBox) {
            return true;
        }

        if (getCurrentPlayer().isInPenalty() && isGettingOutOfPenaltyBox) {
            System.out.println("Answer was correct!!!!");
        } else {
            System.out.println("Answer was corrent!!!!"); // faute orthographe --> pour plus tard
        }

        getCurrentPlayer().increasePurse();
        return didPlayerWin();
    }

    private boolean didPlayerWin() {
        return getCurrentPlayer().getPurse() != 6;
    }

    public void nextPlayer() {
        currentPlayer++;
        if (currentPlayer == howManyPlayers()) {
            currentPlayer = 0;
        }
    }

    public boolean wrongAnswer() { // return always true
        System.out.println("Question was incorrectly answered");
        System.out.println(getCurrentPlayer() + " was sent to the penalty box");
        getCurrentPlayer().setInPenalty(true);
        nextPlayer();
        return true;
    }

}
