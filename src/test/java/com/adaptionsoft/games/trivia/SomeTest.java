package com.adaptionsoft.games.trivia;

import static org.junit.Assert.*;

import com.adaptionsoft.games.uglytrivia.Game;
import org.junit.Test;

import java.util.Random;

public class SomeTest {

	@Test
	public void true_is_true() throws Exception {
		assertTrue(true);
	}

	@Test
	public void main_test() {
		boolean notAWinner;
		long seed = 1;
		Game aGame = new Game();

		aGame.add("Chet");
		aGame.add("Pat");
		aGame.add("Sue");

		Random rand = new Random(seed);

		do {
			aGame.roll(rand.nextInt(5) + 1);

			if (rand.nextInt(9) == 7) {
				notAWinner = aGame.wrongAnswer();
			} else {
				notAWinner = aGame.wasCorrectlyAnswered();
			}
		} while (notAWinner);
	}
}
