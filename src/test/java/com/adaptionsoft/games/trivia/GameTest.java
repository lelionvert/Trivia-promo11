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
        // Given
        boolean notAWinner;
        long seed = 1;
        Random rand = new Random(seed);
        ByteArrayOutputStream actualResult = redirectOutput();
        Game aGame = new Game();

        aGame.add("Chet");
        aGame.add("Pat");
        aGame.add("Sue");

        // When
        do {
            aGame.roll(rand.nextInt(5) + 1);

            if (rand.nextInt(9) == 7) {
                notAWinner = aGame.wrongAnswer();
            }
            else {
                notAWinner = aGame.wasCorrectlyAnswered();
            }
        } while (notAWinner);

        // Then
        String expectedResult = getContentFile("C:\\Users\\Lenovo 30\\Documents\\promo11-trivia\\Trivia-promo11\\resources\\output.txt");
        Assertions.assertThat(actualResult.toString()).isEqualTo(expectedResult);
    }

    private ByteArrayOutputStream redirectOutput() {
        ByteArrayOutputStream actualResult = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(actualResult);
        System.setOut(ps);
        return actualResult;
    }

    private String getContentFile(String filePath) {
        String expectedResult = null;

        try (Stream<String> stream = Files.lines(Paths.get(filePath))) {
            expectedResult = stream.collect(Collectors.joining("\r\n"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return expectedResult;
    }
}
