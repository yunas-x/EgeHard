package org.example.spelling;

import lombok.*;
import org.example.standard.abstractclasses.GameEntry;

@NoArgsConstructor
public class SpellingGameEntry extends GameEntry<Character> {

    public SpellingGameEntry(String word, Character answer, Character incorrectAnswer) {

        super(word, answer);
        this.incorrectAnswer = incorrectAnswer;
    }

    @Getter(AccessLevel.PUBLIC)
    private Character incorrectAnswer;
}
