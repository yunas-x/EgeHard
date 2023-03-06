package org.example.accent;

import lombok.NoArgsConstructor;
import org.example.standard.abstractclasses.GameEntry;

/**
 * Accent game entry model
 */
@NoArgsConstructor
public class AccentGameEntry extends GameEntry<Integer> {
    public AccentGameEntry(String word, Integer correctAnswer) {
        super(word, correctAnswer);
    }

}