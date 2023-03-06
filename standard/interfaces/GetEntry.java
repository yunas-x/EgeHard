package org.example.standard.interfaces;

import org.example.standard.abstractclasses.GameEntry;

import java.util.Optional;

/**
 * Support for getting next item
 * @param <TAnswer> value-type of answer
 */
@FunctionalInterface
public interface GetEntry<TAnswer> {
    /**
     * Provides entries
     * @return new game entry
     * @throws InterruptedException
     */
    Optional<GameEntry<TAnswer>> getNext() throws InterruptedException;
}
