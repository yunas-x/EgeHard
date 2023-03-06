package org.example.standard.abstractclasses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

/**
 * Abstract game entry
 */

@ToString
@EqualsAndHashCode
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class GameEntry<TAnswer> {

    @Getter(AccessLevel.PUBLIC)
    private String word;

    @Getter(AccessLevel.PUBLIC)
    private TAnswer correctAnswer;

    public GameEntry(String word, TAnswer answer) {
        this.word = word;
        this.correctAnswer = answer;
    }
}
