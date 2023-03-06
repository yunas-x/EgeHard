package org.example.wordloadermocks;

import org.example.spelling.SpellingGameEntry;
import org.example.standard.interfaces.WordLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Mock for offline tests
 */
public class WordLoaderSpellingGameOfflineImpl implements WordLoader<Character> {
    @Override
    public List<SpellingGameEntry> Load() {
        List<SpellingGameEntry> gameEntries = new ArrayList<>();

        SpellingGameEntry entry = new SpellingGameEntry("З*ВИДНО", 'О', 'A');
        gameEntries.add(entry);

        entry = new SpellingGameEntry("К*МЕДИАНТ", 'О', 'A');
        gameEntries.add(entry);

        entry = new SpellingGameEntry("БЛ*СТЕТЬ", 'Е', 'И');
        gameEntries.add(entry);

        return gameEntries;
    }
}
