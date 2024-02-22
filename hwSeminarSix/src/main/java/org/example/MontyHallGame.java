package org.example;

import java.util.Random;

public class MontyHallGame {
    private final Random rnd = new Random();

    public boolean play(boolean changeChoice) {
        int car = rnd.nextInt(0,3);
        int playerChoice = rnd.nextInt(0,3);

        if (changeChoice) {
            return playerChoice != car;
        }

        return playerChoice == car;
    }

}
