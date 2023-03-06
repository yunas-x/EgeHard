package org.example.score;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
/*
Add Game Mode Logging
 */
public class ScoreEntityCreate {

    private int totalAnswered;
    private int correctAnswered;

    public ScoreEntityCreate(int tAnswered, int cAnswered) {
        totalAnswered = tAnswered;
        correctAnswered = cAnswered;
    }

    @Setter
    private String gameMode;

}
