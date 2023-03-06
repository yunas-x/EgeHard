package org.example.accent;

import org.example.standard.abstractclasses.StandardWordController;
import org.example.standard.interfaces.WordLoader;

/**
 * Controller for accent game
 */
public class AccentController extends StandardWordController<Integer> {
    public AccentController(WordLoader<Integer> loader) {
        super(loader);
    }
}
