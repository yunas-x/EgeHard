package org.example.wordloadermocks;

import org.example.accent.AccentGameEntry;
import org.example.standard.interfaces.WordLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Mock for offline tests
 */
public class WordLoaderAccentGameOfflineImpl implements WordLoader<Integer> {
    @Override
    public List<AccentGameEntry> Load() {
        List<AccentGameEntry> gameEntries = new ArrayList<>();

        AccentGameEntry entry = new AccentGameEntry("ЗАВИДНО", 4);
        gameEntries.add(entry);

        entry = new AccentGameEntry("АЭРОПОРТЫ", 6);
        gameEntries.add(entry);

        entry = new AccentGameEntry("ТУФЛЯ", 2);
        gameEntries.add(entry);

        return gameEntries;
    }
}
