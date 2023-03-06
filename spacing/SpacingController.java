package org.example.spacing;

import lombok.AccessLevel;
import lombok.Getter;
import org.example.standard.abstractclasses.StandardController;
import org.example.standard.interfaces.WordLoader;

import java.util.ArrayList;
import java.util.Optional;

public class SpacingController
        extends StandardController<SpacingVariants> {

    // When stating question check if the very variant exists
    // i.e. if no hyphen-spaced, don't ask to find them
    // place 3-4 sentences on screen. Multi-variant

    public static final int quantityOfQuestions = 4;

    @Getter(AccessLevel.PUBLIC)
    private SpacingGameUnit currentGameUnit;

    public SpacingController(WordLoader<SpacingVariants> loader) {

        super(loader);
    }

    @Override
    public Optional<Boolean> answerIsCorrect(SpacingVariants spacingVariant) {

        // Check if game entry exists and pointer is well put
        if (currentGameUnit == null || isGameOverOrBroken()) {

            return Optional.empty();
        }

        getStats().totalAnswersGiven++;

        boolean result = spacingVariant.equals(currentGameUnit.getAnswerVariantToFind());

        if (result)
            getStats().correctAnswersGiven++;

        return Optional.of(result);
    }

    /**
     * Selects and returns four items
     * @return items for spacing game. Returns empty container if load is scarce or empty
     */
    public Optional<SpacingGameUnit> GetNext() throws InterruptedException {
        if (gameEntries == null || gameEntries.size() < quantityOfQuestions) {
            return Optional.empty();
        }

        var spacingGameEntries = new ArrayList<SpacingGameEntry>(4);

        for (int i = 0; i < quantityOfQuestions; i++) {
            getReloader().moveEntriesPointerAndReload();
            getReloader().joinReloadsIfExists();
            spacingGameEntries.add((SpacingGameEntry) gameEntries.get(getPointer().getIndex()));
        }

        currentGameUnit = new SpacingGameUnit(spacingGameEntries);

        return Optional.of(currentGameUnit);
    }


}
