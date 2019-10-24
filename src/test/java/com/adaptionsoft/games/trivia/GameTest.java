package com.adaptionsoft.games.trivia;

import com.adaptionsoft.games.uglytrivia.Game;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GameTest {


    @Test
    public void main_test() {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        System.setOut(ps);

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

        String stringBuilder = null;

		try (Stream<String> stream = Files.lines(Paths.get("C:\\Users\\Lenovo 30\\Documents\\promo11-trivia\\Trivia-promo11\\resources\\output.txt"))) {

            stringBuilder = stream.collect(Collectors.joining("\r\n"));

		} catch (IOException e) {
			e.printStackTrace();
		}



        Assertions.assertThat(baos.toString()).isEqualTo(stringBuilder);

	}
}
