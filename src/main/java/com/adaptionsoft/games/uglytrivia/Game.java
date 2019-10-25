package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Game {
    ArrayList players = new ArrayList();
    int[] places = new int[6];
    int[] purses = new int[6];
    boolean[] inPenaltyBox = new boolean[6];

    // Duplication -> Same class
    QuestionStack popQuestionStack;
    QuestionStack scienceQuestionStack;
    QuestionStack sportsQuestionStack;
    QuestionStack rockQuestionStack;
    
    Map<QuestionCategory, QuestionStack> questionCategoryStack;

    int currentPlayer = 0;
    boolean isGettingOutOfPenaltyBox;

    public Game() {
        popQuestionStack = QuestionStack.initializeStack(QuestionCategory.POP);
        scienceQuestionStack = QuestionStack.initializeStack(QuestionCategory.SCIENCE);
        sportsQuestionStack = QuestionStack.initializeStack(QuestionCategory.SPORT);
        rockQuestionStack = QuestionStack.initializeStack(QuestionCategory.ROCK);
        
        questionCategoryStack = new HashMap<>();
        questionCategoryStack.put(QuestionCategory.POP, popQuestionStack);
        questionCategoryStack.put(QuestionCategory.SCIENCE, scienceQuestionStack);
        questionCategoryStack.put(QuestionCategory.SPORT, sportsQuestionStack);
        questionCategoryStack.put(QuestionCategory.ROCK, rockQuestionStack);
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
        questionCategoryStack.get(currentCategory()).printFirstQuestion();
    }

    private boolean isOdd(int roll) {
        return roll % 2 != 0;
    }

    private boolean isEven(int roll) {
        return roll % 2 == 0;
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
