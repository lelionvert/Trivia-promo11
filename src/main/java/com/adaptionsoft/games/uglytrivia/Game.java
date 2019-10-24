package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;
import java.util.LinkedList;

public class Game {
    ArrayList players = new ArrayList();
    int[] places = new int[6];
    int[] purses  = new int[6];
    boolean[] inPenaltyBox  = new boolean[6];

    // Duplication -> Same class
    LinkedList popQuestions = new LinkedList();
    LinkedList scienceQuestions = new LinkedList();
    LinkedList sportsQuestions = new LinkedList();
    LinkedList rockQuestions = new LinkedList();

    int currentPlayer = 0;
    boolean isGettingOutOfPenaltyBox;

    public  Game(){
    	for (int i = 0; i < 50; i++) {
			popQuestions.addLast("Pop Question " + i);
			scienceQuestions.addLast(("Science Question " + i));
			sportsQuestions.addLast(("Sports Question " + i));
			rockQuestions.addLast(createRockQuestion(i));
    	}
    }

    // Same as others
	public String createRockQuestion(int index){
		return "Rock Question " + index;
	}

	// Not used
	public boolean isPlayable() {
		return (howManyPlayers() >= 2);
	}

	// Change signature, name, one responsability thing
	public boolean add(String playerName) {
	    players.add(playerName);
	    // several calls of howManyPlayers
		//extract methods?
	    places[howManyPlayers()] = 0;
	    purses[howManyPlayers()] = 0;
	    inPenaltyBox[howManyPlayers()] = false;

	    System.out.println(playerName + " was added");
	    System.out.println("They are player number " + players.size());//duplication cachée
		return true;
	}

	public int howManyPlayers() {
		return players.size();
	}

	public void roll(int roll) {
		System.out.println(players.get(currentPlayer) + " is the current player");
		System.out.println("They have rolled a " + roll);//faute orthographe --> pour plus tard
		//imbrication des conditions
		if (inPenaltyBox[currentPlayer]) {
			if (roll % 2 == 0) {
				isGettingOutOfPenaltyBox = false;
				System.out.println(players.get(currentPlayer) + " is not getting out of the penalty box");
			} else {
				isGettingOutOfPenaltyBox = true;
				System.out.println(players.get(currentPlayer) + " is getting out of the penalty box");
				movePlayer(roll);
			}
		} else {
			movePlayer(roll);
		}
    }


	private void movePlayer(int roll) {
		places[currentPlayer] = places[currentPlayer] + roll;
		if (places[currentPlayer] > 11) {//11 & 12 = magic numbers?
			places[currentPlayer] = places[currentPlayer] - 12;
		}
		System.out.println(players.get(currentPlayer) + "'s new location is " + places[currentPlayer]);
		System.out.println("The category is " + currentCategory());
		askQuestion();
	}

	private void askQuestion() {//one responsability
		if (currentCategory() == "Pop")//qqc avec egal?
			System.out.println(popQuestions.removeFirst());
		if (currentCategory() == "Science")
			System.out.println(scienceQuestions.removeFirst());
		if (currentCategory() == "Sports")
			System.out.println(sportsQuestions.removeFirst());
		if (currentCategory() == "Rock")
			System.out.println(rockQuestions.removeFirst());
	}


	private String currentCategory() {//conditions a refaire? rearrange ?
		if (places[currentPlayer] == 0) return "Pop";
		if (places[currentPlayer] == 4) return "Pop";
		if (places[currentPlayer] == 8) return "Pop";
		if (places[currentPlayer] == 1) return "Science";
		if (places[currentPlayer] == 5) return "Science";
		if (places[currentPlayer] == 9) return "Science";
		if (places[currentPlayer] == 2) return "Sports";
 		if (places[currentPlayer] == 6) return "Sports";
		if (places[currentPlayer] == 10) return "Sports";
		return "Rock";
	}

	public boolean wasCorrectlyAnswered() {//plusieurs responsabilites? renommer?
    	boolean winner = true;

		if (inPenaltyBox[currentPlayer]){
			if (isGettingOutOfPenaltyBox) {
				System.out.println("Answer was correct!!!!");
				//duplication
				purses[currentPlayer]++;
				System.out.println(players.get(currentPlayer) + " now has " + purses[currentPlayer] + " Gold Coins.");
				winner = didPlayerWin();
				nextPlayer();
				//---
			} else {
				nextPlayer();
			}
		} else {
			System.out.println("Answer was corrent!!!!");//faute orthographe --> pour plus tard
			//duplication
			purses[currentPlayer]++;
			System.out.println(players.get(currentPlayer) + " now has " + purses[currentPlayer] + " Gold Coins.");
			winner = didPlayerWin();
			nextPlayer();
			//---
		}
		return winner;
	}

	private void nextPlayer() {
		currentPlayer++;
		if (currentPlayer == players.size()) {
			currentPlayer = 0;
		}
	}

	public boolean wrongAnswer(){//return always true
		System.out.println("Question was incorrectly answered");
		System.out.println(players.get(currentPlayer)+ " was sent to the penalty box");
		inPenaltyBox[currentPlayer] = true;
		//duplication
		nextPlayer();
		return true;
	}


	private boolean didPlayerWin() {
		return !(purses[currentPlayer] == 6);
	}
}
