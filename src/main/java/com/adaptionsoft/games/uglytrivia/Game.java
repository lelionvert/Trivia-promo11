package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;
import java.util.LinkedList;

public class Game {
    ArrayList players = new ArrayList();
    int[] places = new int[6];
    int[] purses = new int[6];
    boolean[] inPenaltyBox = new boolean[6];

    // Duplication -> Same class
    LinkedList popQuestions = new LinkedList();
    LinkedList scienceQuestions = new LinkedList();
    LinkedList sportsQuestions = new LinkedList();
    LinkedList rockQuestions = new LinkedList();

    int currentPlayer = 0;
    boolean isGettingOutOfPenaltyBox;

    public Game() {
        for (int i = 0; i < 50; i++) {
            popQuestions.addLast("Pop Question " + i);
            scienceQuestions.addLast("Science Question " + i);
            sportsQuestions.addLast("Sports Question " + i);
            rockQuestions.addLast("Rock Question " + i);
        }
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

        System.out.println(playerName + " was added");
        System.out.println("They are player number " + numberPlayers);
    }

    private void initPlayerState(int numberPlayers) {
        places[numberPlayers] = 0;
        purses[numberPlayers] = 0;
        inPenaltyBox[numberPlayers] = false;
    }

    private void movePlayer(int roll) {
        places[currentPlayer] = (places[currentPlayer] + roll) % 12;
        System.out.println(players.get(currentPlayer) + "'s new location is " + places[currentPlayer]);
    }

    private void increasePursePlayer() {
        purses[currentPlayer]++;
        System.out.println(players.get(currentPlayer) + " now has " + purses[currentPlayer] + " Gold Coins.");
    }

    public int howManyPlayers() {
        return players.size();
    }

    public void roll(int roll) {
        System.out.println(players.get(currentPlayer) + " is the current player");
        System.out.println("They have rolled a " + roll);//faute orthographe --> pour plus tard

        boolean isCurrentPlayerInPenaltyBox = this.inPenaltyBox[currentPlayer];

        if (isCurrentPlayerInPenaltyBox && isEven(roll)) {
            // Extract in method enterPenaltyBox
            isGettingOutOfPenaltyBox = false;
            System.out.println(players.get(currentPlayer) + " is not getting out of the penalty box");
            return;
        }

        if (isCurrentPlayerInPenaltyBox && isOdd(roll)) {
            // Extract in method exitPenaltyBox
            isGettingOutOfPenaltyBox = true;
            System.out.println(players.get(currentPlayer) + " is getting out of the penalty box");
        }

        movePlayer(roll);
        currentCategory().print();
        askQuestion();
    }

    private boolean isOdd(int roll) {
        return roll % 2 != 0;
    }

    private boolean isEven(int roll) {
        return roll % 2 == 0;
    }

    private void askQuestion() {//one responsability
        if (currentCategory() == QuestionCategory.POP)//qqc avec egal?
            System.out.println(popQuestions.removeFirst());
        if (currentCategory() == QuestionCategory.SCIENCE)
            System.out.println(scienceQuestions.removeFirst());
        if (currentCategory() == QuestionCategory.SPORT)
            System.out.println(sportsQuestions.removeFirst());
        if (currentCategory() == QuestionCategory.ROCK)
            System.out.println(rockQuestions.removeFirst());
    }

    private QuestionCategory currentCategory() {
        return QuestionCategory.getCategoryBy(places[currentPlayer]);
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
        System.out.println(players.get(currentPlayer) + " was sent to the penalty box");
        inPenaltyBox[currentPlayer] = true;
        nextPlayer();
        return true;
    }

}
