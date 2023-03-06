package org.example.spacing;

import lombok.AccessLevel;
import lombok.Getter;
import org.example.standard.abstractclasses.GameEntry;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SpacingGameUnit {

    @Getter(AccessLevel.PUBLIC)
    private SpacingVariants answerVariantToFind;

    @Getter(AccessLevel.PUBLIC)
    private ArrayList<SpacingGameEntry> spacingGameEntries;

    private static final Random rnd = new Random();

    /**
     * Sets spacing game entries if valid
     * @return true on set, false on fail
     */
    private boolean setSpacingGameEntries(ArrayList<SpacingGameEntry> gameEntryArrayList) {
        if (gameEntryArrayList == null || gameEntryArrayList.size() < SpacingController.quantityOfQuestions)
            return false;
        else
            if (gameEntryArrayList.size() == SpacingController.quantityOfQuestions)
                spacingGameEntries = gameEntryArrayList;
            else
                spacingGameEntries = (ArrayList<SpacingGameEntry>) gameEntryArrayList
                                            .subList(0, SpacingController.quantityOfQuestions);

        return true;
    }

    private void setAnswerVariantToFind() {
        var answerVariants = spacingGameEntries
                .stream()
                .map(GameEntry::getCorrectAnswer)
                .distinct()
                .toList();

        answerVariantToFind = answerVariants.get(rnd.nextInt(0, answerVariants.size()));
    }

    /**
     * Finds and collects correct entries
     * @return correct entries for answerVariantToFind
     */
    public List<SpacingGameEntry> getCorrectEntries() {
        return spacingGameEntries
                .stream()
                .filter(e -> e.getCorrectAnswer().equals(answerVariantToFind))
                .toList();
    }

    /**
     * Finds correct items
     * @return correct answers' indices
     */
    public List<Integer> getCorrectEntryIndices() {
        List<Integer> indices = new ArrayList<Integer>();

        for (var i = 0; i < spacingGameEntries.size(); i++) {

            // if i-th item is correct
            if (spacingGameEntries.get(i).getCorrectAnswer().equals(answerVariantToFind))
                indices.add(i); // add ind
        }

        return indices;
    }

    protected SpacingGameUnit(ArrayList<SpacingGameEntry> gameEntries) {
        if (setSpacingGameEntries(gameEntries))
            setAnswerVariantToFind();
        else
            throw new IllegalArgumentException("gameEntries are broken");
    }
}
