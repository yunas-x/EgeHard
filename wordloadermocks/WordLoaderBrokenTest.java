package org.example.wordloadermocks;

import org.example.standard.abstractclasses.GameEntry;
import org.example.standard.interfaces.WordLoader;

import java.util.List;

/**
 * Mock for offline tests
 */
public class WordLoaderBrokenTest implements WordLoader<Integer> {
    @Override
    public List<GameEntry<Integer>> Load() {
        return null;
    }
}
