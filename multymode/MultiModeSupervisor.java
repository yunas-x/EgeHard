package org.example.multymode;

import org.example.accent.AccentController;
import org.example.connection.score.ScoreOperations;
import org.example.spacing.SpacingController;
import org.example.spelling.SpellingController;
import org.example.score.AnswerStats;

import java.io.IOException;
import java.util.Optional;
import java.util.Random;

public class MultiModeSupervisor {

    SpacingController spacingController;
    AccentController accentController;
    SpellingController spellingController;

    static Random rand = new Random();

    public int getCorrectAnswersGiven() {
        return spacingController.getStats().correctAnswersGiven
                + accentController.getStats().correctAnswersGiven
                + spellingController.getStats().correctAnswersGiven;
    }

    public int getTotalAnswersGiven() {
        return spacingController.getStats().totalAnswersGiven
                + accentController.getStats().totalAnswersGiven
                + spellingController.getStats().totalAnswersGiven;
    }

    public int getIncorrectAnswersGiven() {
        return getTotalAnswersGiven() - getCorrectAnswersGiven();
    }

    public MultiModeSupervisor(SpacingController spacingController,
                               AccentController accentController,
                               SpellingController spellingController) {

        this.accentController = accentController;
        this.spacingController = spacingController;
        this.spellingController = spellingController;

    }

    /**
     * Do this on game over (sends stats to server)
     * @throws IOException
     */
    public void onGameOver() throws IOException {
        new ScoreOperations().scoreSend(new AnswerStats(getCorrectAnswersGiven(), getTotalAnswersGiven()),
                                        this.getClass().getName());
    }

    /**
     * Yields game entries
     * @return Random Game Entry or Unit
     * @throws InterruptedException
     */
    public Optional<Object> getNext() throws InterruptedException {

        int option = rand.nextInt(0, 3);

        switch (option) {
            case 0 -> {
                return Optional.of(accentController.getNext());
            }
            case 1 -> {
                return Optional.of(spacingController.GetNext());
            }
            case 2 -> {
                return Optional.of(spellingController.getNext());
            }
            default -> {
                return Optional.empty();
            }
        }
    }
}
