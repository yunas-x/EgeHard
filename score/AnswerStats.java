package org.example.score;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.example.score.ScoreEntityCreate;

/**
 * Data class for stats collection
 */
@AllArgsConstructor
@NoArgsConstructor
public class AnswerStats {
    public int correctAnswersGiven = 0;

    public int totalAnswersGiven = 0;

    /**
     * Counts how many incorrect answers were given
     * @return totalAnswers - correctAnswers
     */
    public int incorrectAnswersGiven() {
        return this.totalAnswersGiven - this.correctAnswersGiven;
    }

    /**
     * Converts to ScoreEntityCreate
     * @return matching ScoreEntityCreate
     */
    public ScoreEntityCreate toScoreEntityCreate(String gameMode) {
        return new ScoreEntityCreate(totalAnswersGiven, correctAnswersGiven, gameMode);
    }
}
