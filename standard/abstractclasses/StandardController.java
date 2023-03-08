package org.example.standard.abstractclasses;

import lombok.Getter;
import org.example.connection.score.ScoreOperations;
import org.example.score.AnswerStats;
import org.example.standard.interfaces.WordLoader;

import java.io.IOException;
import java.util.Optional;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicBoolean;


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
    protected Stack<GameEntry<TAnswer>> gameEntries = new Stack<>();

    private final WordLoader<TAnswer> loader;

    /**
     * Stats for the current game
     */
    @Getter
    private final AnswerStats stats;

    @Getter
    private final Reloader reloader;


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
                || (gameEntries.isEmpty()
                && !reloader.reloaded.get());
    }


    /**
     * Sets loader and loads first collection of entities
     * @param loader manufacture-class, gets game entries
     */
    public StandardController(WordLoader<TAnswer> loader) {
        stats = new AnswerStats();
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
            reloader.reloaded.compareAndSet(true, false);
        }
    }


    /**
     * Reloads controller
     */
    protected class Reloader {

        /**
         * If reload is successful
         */
        private volatile AtomicBoolean reloaded = new AtomicBoolean(true);

        /**
         * Async reload
         */
        private Thread reload;


        /**
         * Reloads if null or dead
         */
        public synchronized void reloadIfRequired() throws InterruptedException {

            if (!reloaded.get()) {
                throw new IndexOutOfBoundsException("Кончились задачи");
            }

            if ((reload == null || !reload.isAlive())
                    && StandardController.this.gameEntries.size() <= 2) {
                reload = new Thread(StandardController.this::loadWords);
                reload.start();
            }
        }

        /**
         * Wait if not reloaded
         * @throws InterruptedException
         */
        public synchronized void joinReloadsIfExists() throws InterruptedException {
            if (StandardController.this.gameEntries.isEmpty() && reload != null)
                reload.join();
        }


    }


}


