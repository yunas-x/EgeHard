package org.example.score;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class ScoreEntityDisplay extends ScoreEntityCreate {

    @Getter
    private Date playedOn;

    public ScoreEntityDisplay(int totalAnswered, int correctAnswered, Date dateTime) {
        super(totalAnswered, correctAnswered);
        playedOn = dateTime;

    }

    public ScoreEntityDisplay(int totalAnswered, int correctAnswered, String mode, Date dateTime) {
        super(totalAnswered, correctAnswered, mode);
        playedOn = dateTime;

    }
}
