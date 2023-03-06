package org.example.spacing;

import lombok.NoArgsConstructor;
import org.example.standard.abstractclasses.GameEntry;

@NoArgsConstructor
public class SpacingGameEntry extends GameEntry<SpacingVariants> {

    public SpacingGameEntry(String word, SpacingVariants spacingVariants) {
        super(word, spacingVariants);
    }


}
