package org.example.spelling;

import org.example.standard.abstractclasses.StandardWordController;
import org.example.standard.interfaces.WordLoader;

public class SpellingController extends StandardWordController<Character> {

    public SpellingController(WordLoader<Character> loader) {
        super(loader);
    }
}
