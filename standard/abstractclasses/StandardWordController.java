package org.example.standard.abstractclasses;

import org.example.standard.interfaces.GetEntry;
import org.example.standard.interfaces.WordLoader;

import java.util.Optional;

/**
 * Standard for Accents and Spelling games
 * @param <TAnswer> type of answer
 */
public abstract class StandardWordController<TAnswer>
        extends StandardController<TAnswer>
        implements GetEntry<TAnswer> {


    public StandardWordController(WordLoader<TAnswer> loader) {
        super(loader);
    }

    /**
     * Gets next entry if exists
     *
     * @return filled container if value exists, otherwise empty one
     */
    public Optional<GameEntry<TAnswer>> getNext() throws InterruptedException {

        if (gameEntries == null || gameEntries.size() == 0) {
            return Optional.empty();
        }

        getReloader().moveEntriesPointerAndReload();
        getReloader().joinReloadsIfExists();

        return Optional.ofNullable(gameEntries.get(getPointer().getIndex()));
    }

    /**
     * @param answer provided by player
     * @return true if answer is correct, otherwise false
     */
    @Override
    public Optional<Boolean> answerIsCorrect(TAnswer answer) {
        {

            // Check if game entry exists and pointer is well put
            if (isGameOverOrBroken()) {

                return Optional.empty();
            }

            getStats().totalAnswersGiven++;

            boolean result = gameEntries.get(getPointer().getIndex()).getCorrectAnswer().equals(answer);

            if (result)
                getStats().correctAnswersGiven++;

            return Optional.of(result);
        }
    }
}