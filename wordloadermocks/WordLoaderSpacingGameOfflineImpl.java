package org.example.wordloadermocks;

import org.example.spacing.SpacingVariants;
import org.example.spacing.SpacingGameEntry;
import org.example.standard.interfaces.WordLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Mock for offline tests
 */
public class WordLoaderSpacingGameOfflineImpl implements WordLoader<SpacingVariants> {
    @Override
    public List<SpacingGameEntry> Load() {
        List<SpacingGameEntry> gameEntries = new ArrayList<>();

        SpacingGameEntry entry = new SpacingGameEntry("Все()таки он хотел не это", SpacingVariants.Hyphen);
        gameEntries.add(entry);

        entry = new SpacingGameEntry("Кое()как он дошел до больницы", SpacingVariants.Hyphen);
        gameEntries.add(entry);

        entry = new SpacingGameEntry("Чтобы стать счастливым, нужно найти свою идею счастья", SpacingVariants.NoSpace);
        gameEntries.add(entry);

        entry = new SpacingGameEntry("Что бы ни случилось, иди вперед", SpacingVariants.WhiteSpace);
        gameEntries.add(entry);

        return gameEntries;
    }
}
