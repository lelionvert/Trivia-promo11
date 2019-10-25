package com.adaptionsoft.games.uglytrivia;

public class Game {
    Players players;
    int[] purses = new int[6];
    boolean[] inPenaltyBox = new boolean[6];
    
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

        int numberPlayers = howManyPlayers();
        initPlayerState(numberPlayers);
    }

    private void initPlayerState(int numberPlayers) {
        purses[numberPlayers] = 0;
        inPenaltyBox[numberPlayers] = false;
    }

    private void movePlayer(int roll) {
        // Demeter
        players.getPlayerByIndex(currentPlayer).move(roll);
    }

    private void increasePursePlayer() {
        purses[currentPlayer]++;
        System.out.println(players.getPlayerByIndex(currentPlayer) + " now has " + purses[currentPlayer] + " Gold Coins.");
    }

    public int howManyPlayers() {
        return players.size();
    }

    public void roll(int roll) {
        System.out.println(players.getPlayerByIndex(currentPlayer) + " is the current player");
        System.out.println("They have rolled a " + roll);//faute orthographe --> pour plus tard

        boolean isCurrentPlayerInPenaltyBox = this.inPenaltyBox[currentPlayer];

        if (isCurrentPlayerInPenaltyBox && isEven(roll)) {
            // Extract in method enterPenaltyBox
            isGettingOutOfPenaltyBox = false;
            System.out.println(players.getPlayerByIndex(currentPlayer) + " is not getting out of the penalty box");
            return;
        }

        if (isCurrentPlayerInPenaltyBox && isOdd(roll)) {
            // Extract in method exitPenaltyBox
            isGettingOutOfPenaltyBox = true;
            System.out.println(players.getPlayerByIndex(currentPlayer) + " is getting out of the penalty box");
        }

        movePlayer(roll);
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
        return QuestionCategory.getCategoryBy(players.getPlayerByIndex(currentPlayer).getPlace());
    }

    public boolean wasCorrectlyAnswered() { // plusieurs responsabilites? renommer?
        if (inPenaltyBox[currentPlayer] && !isGettingOutOfPenaltyBox) {
            return true;
        }

        if (inPenaltyBox[currentPlayer] && isGettingOutOfPenaltyBox) {
            System.out.println("Answer was correct!!!!");
        } else {
            System.out.println("Answer was corrent!!!!"); // faute orthographe --> pour plus tard
        }

        increasePursePlayer();
        return didPlayerWin();
    }

    private boolean didPlayerWin() {
        return purses[currentPlayer] != 6;
    }

    public void nextPlayer() {
        currentPlayer++;
        if (currentPlayer == howManyPlayers()) {
            currentPlayer = 0;
        }
    }

    public boolean wrongAnswer() { // return always true
        System.out.println("Question was incorrectly answered");
        System.out.println(players.getPlayerByIndex(currentPlayer) + " was sent to the penalty box");
        inPenaltyBox[currentPlayer] = true;
        nextPlayer();
        return true;
    }

}
