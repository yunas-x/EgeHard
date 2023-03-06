package org.example.standard.interfaces;

import org.example.standard.abstractclasses.GameEntry;

import java.io.IOException;
import java.util.List;

/**
 * Supports loading of game entries
 * @param <T> value-type of answer
 */
@FunctionalInterface
public interface WordLoader<T> {

    /**
     * Loads game-entries
     * @return List of entries
     */
    List<? extends GameEntry<T>> Load() throws IOException;
}
