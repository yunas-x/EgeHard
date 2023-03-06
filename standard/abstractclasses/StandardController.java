package org.example.standard.abstractclasses;

import lombok.Getter;
import org.example.connection.score.ScoreOperations;
import org.example.score.AnswerStats;
import org.example.standard.interfaces.WordLoader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Stack;


/**
 * The standard controller
 * @param <TAnswer> type of answer
 */
public abstract class StandardController<TAnswer> {

    /// Move reloading to another class

    /**
     * Game Entries for a game
     */
    @Getter
    protected List<GameEntry<TAnswer>> gameEntries = new ArrayList<>();

    private final WordLoader<TAnswer> loader;

    /**
     * Stats for the current game
     */
    @Getter
    private final AnswerStats stats;

    @Getter
    private final Reloader reloader;

    @Getter
    private final EntryPointer pointer;

    /**
     * On game over sends stats
     * @throws IOException on parsing
     */
    public void onGameOver() throws IOException {
        new ScoreOperations().scoreSend(stats, this.getClass().getName());
    }

    /**
     * Check if game is invalid
     */
    public boolean isGameOverOrBroken() {

        return gameEntries == null
                || gameEntries.isEmpty()
                || pointer.getIndex() == -1
                || pointer.getIndex() >= gameEntries.size()
                || !reloader.reloaded;
    }


    /**
     * Sets loader and loads first collection of entities
     * @param loader manufacture-class, gets game entries
     */
    public StandardController(WordLoader<TAnswer> loader) {
        stats = new AnswerStats();
        pointer = new EntryPointer();
        this.loader = loader;
        reloader = new Reloader();
        loadWords();
    }

    /**
     * Checks if answer is correct
     * @param answer provided by player
     * @return empty Optional on incorrect condition, otherwise the result of validation
     */
    abstract public Optional<Boolean> answerIsCorrect(TAnswer answer);

    /**
     * Loads words from loader
     */
    protected void loadWords() {
        try {
            gameEntries.addAll(loader.Load());
        }
        catch (Exception e) {
            reloader.reloaded = false;
        }
    }


    /**
     * Reloads controller
     */
    protected class Reloader {

        /**
         * If reload is successful
         */
        private boolean reloaded = true;

        /**
         * Async reload
         */
        protected Thread reloads;

        /**
         * Moves game entries pointer one step forward
         * Reloads gameEntries if they are over
         */
        public void moveEntriesPointerAndReload() throws IndexOutOfBoundsException {
            if (!reloaded) {
                throw new IndexOutOfBoundsException("Кончились задачи");
            }

            StandardController.this.getPointer().movePointer();
            reloadIfRequired();
        }

        /**
         * Reloads if null or dead
         */
        private void reloadIfRequired() {
            if ((reloads == null || !reloads.isAlive())
                    && StandardController.this.gameEntries.size()
                    - StandardController.this.getPointer().getIndex() <= 2) {
                reloads = new Thread(StandardController.this::loadWords);
                reloads.start();
            }
        }

        /**
         * Wait if not reloaded
         * @throws InterruptedException
         */
        public void joinReloadsIfExists() throws InterruptedException {
            if (StandardController.this.getPointer().getIndex()
                    == StandardController.this.gameEntries.size()
                    && reloads != null)

                reloads.join();
        }


    }

    protected static class EntryPointer {

        /**
         * Pointer for selecting entries
         */
        @Getter
        private int index = -1;

        /**
         * Adds one to index
         */
        private void movePointer() {
            index++;
        }
    }

}


