package org.example.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.accent.AccentController;
import org.example.accent.AccentGameEntry;
import org.example.connection.player.PlayerOperations;
import org.example.connection.player.PlayerRequestException;
import org.example.connection.score.ScoreOperations;
import org.example.connection.wordloaders.WordLoaderAccentGameOnlineImpl;
import org.example.multymode.MultiModeSupervisor;
import org.example.player.PlayerEntityCreate;
import org.example.player.PlayerValidateEntity;
import org.example.spacing.SpacingController;
import org.example.spacing.SpacingGameEntry;
import org.example.spacing.SpacingVariants;
import org.example.spelling.SpellingController;
import org.example.spelling.SpellingGameEntry;
import org.example.score.AnswerStats;
import org.example.wordloadermocks.WordLoaderAccentGameOfflineImpl;
import org.example.wordloadermocks.WordLoaderSpacingGameOfflineImpl;
import org.example.wordloadermocks.WordLoaderSpellingGameOfflineImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class PreliminaryTests {

    private static MultiModeSupervisor superviser;
    private static AccentController accentController;
    private static SpellingController spellingController;
    private static SpacingController spacingController;

    @BeforeAll
    public static void InitializeControllers() {

        spacingController = new SpacingController(new WordLoaderSpacingGameOfflineImpl());
        accentController = new AccentController(new WordLoaderAccentGameOfflineImpl());
        spellingController =  new SpellingController(new WordLoaderSpellingGameOfflineImpl());

        superviser = new MultiModeSupervisor(
                spacingController,
                accentController,
                spellingController
        );
    }

    @Test
    public void HundredIterationsTest_ShouldPass() throws InterruptedException {

        int i = 0;
        while (i < 100) {
            if (superviser.getNext().isPresent())
                i++;

        }

        Assertions.assertEquals(100, i);
    }

    @Test
    public void SpellingControllerTest_OfflineLoaderGetNext_YieldGameEntry() throws InterruptedException {

        SpellingController controller = new SpellingController(
                new WordLoaderSpellingGameOfflineImpl() {
                    @Override
                    public List<SpellingGameEntry> Load() {
                        var list = new ArrayList<SpellingGameEntry>();
                        list.add(new SpellingGameEntry("ПР*ВЕТ", 'И', 'Е'));
                        return list;
                    }
                }
        );


        controller.getNext().ifPresent(g -> {
            var p = (SpellingGameEntry) g;
            Assertions.assertEquals(p.getIncorrectAnswer(), 'Е');
        });

    }

    @Test
    public void SpacingControllerTest_OfflineLoaderGetNext_IsPresent() throws InterruptedException {
        Assertions.assertTrue(spacingController.GetNext().isPresent());
    }

    @Test
    public void SpacingControllerTest_OfflineLoaderGetNext_Reload() throws InterruptedException {
        spacingController.GetNext();
        spacingController.GetNext();
        Assertions.assertTrue(spacingController.GetNext().isPresent());
    }

    @Test
    public void SpacingControllerTest_MockLoader_CorrectAnswerToFind() throws InterruptedException {
        var controller = new SpacingController(new WordLoaderSpacingGameOfflineImpl() {
            @Override
            public List<SpacingGameEntry> Load() {
                List<SpacingGameEntry> gameEntries = new ArrayList<>();

                SpacingGameEntry entry = new SpacingGameEntry("кака", SpacingVariants.Hyphen);
                gameEntries.add(entry);
                gameEntries.add(entry);
                gameEntries.add(entry);
                gameEntries.add(entry);
                gameEntries.add(entry);

                return gameEntries;
            }
        });

        controller.GetNext();
        var gameUnit = controller.GetNext();

        Assertions.assertEquals(gameUnit.get().getAnswerVariantToFind(), SpacingVariants.Hyphen);
    }

    @Test
    public void SpacingControllerTest_ScarceInputInLoader_ThrowsOnGet() throws InterruptedException {
        var controller = new SpacingController(new WordLoaderSpacingGameOfflineImpl() {
            @Override
            public List<SpacingGameEntry> Load() {
                List<SpacingGameEntry> gameEntries = new ArrayList<>();

                SpacingGameEntry entry = new SpacingGameEntry("кака", SpacingVariants.Hyphen);
                gameEntries.add(entry);

                return gameEntries;
            }
        });

        controller.GetNext();
        var gameUnit = controller.GetNext();

        Assertions.assertThrows(NoSuchElementException.class, gameUnit::get);
    }

    @Test
    public void AccentGameEntry_Deserialize_CheckOnGenerated() throws JsonProcessingException {
        AccentGameEntry gameEntry = new AccentGameEntry("Альфа", 1);
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(gameEntry);
        AccentGameEntry newEntry = mapper.readValue(json, AccentGameEntry.class);

        Assertions.assertEquals(gameEntry, newEntry);

    }

    @Test
    public void SpellingGameEntry_Deserialize_CheckOnGenerated() throws JsonProcessingException {
        SpellingGameEntry gameEntry = new SpellingGameEntry("Aльфа", 'A', 'Б');
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(gameEntry);
        SpellingGameEntry newEntry = mapper.readValue(json, SpellingGameEntry.class);

        Assertions.assertEquals(gameEntry, newEntry);

    }

    @Test
    public void SpacingGameEntry_Deserialize_CheckOnGenerated() throws JsonProcessingException {
        SpacingGameEntry gameEntry = new SpacingGameEntry("Альфа", SpacingVariants.WhiteSpace);
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(gameEntry);
        System.out.println(json);
        SpacingGameEntry newEntry = mapper.readValue(json, SpacingGameEntry.class);

        Assertions.assertEquals(gameEntry, newEntry);

    }

    @Test
    public void IntegrationTest_AccentEntry_GetFromServer() throws IOException {
        WordLoaderAccentGameOnlineImpl wordLoader = new WordLoaderAccentGameOnlineImpl();
        wordLoader.setURL("http://127.0.0.1:8000/accents/10");
        var load = wordLoader.Load();
        Assertions.assertEquals(10, load.size());
    }

    @Test
    public void IntegrationTest_GetScore_GetFromServer() throws IOException {
        var so = new ScoreOperations();
        so.setURL("http://127.0.0.1:8000/users/stats/id=1");
        var lst = so.getScoreStory();
        System.out.println(lst.get(lst.size()-1).getGameMode().toString());
    }

    @Test
    public void IntegrationTest_PostScore_PostToServer() throws IOException {
        var so = new ScoreOperations();
        so.setURL("http://127.0.0.1:8000/postscore/id=1");
        so.scoreSend(new AnswerStats(1, 2), "Mock");
    }

    @Test
    public void IntegrationTest_PostPlayer_PostToServer() throws IOException, NoSuchAlgorithmException, PlayerRequestException {
        var so = new PlayerOperations();
        so.setURL("http://127.0.0.1:8000/postplayer");
        var p = new PlayerEntityCreate();
        p.setEmail("456");
        p.setName("456");
        p.setPassword("pass");
        var r = so.createPlayer(p);
        var name = so.getPlayerFromJson(r);
        System.out.println(name.getId());
    }

    @Test
    public void IntegrationTest_ValidatePlayer_PostToServer() throws IOException, NoSuchAlgorithmException, PlayerRequestException {
        var so = new PlayerOperations();
        so.setURL("http://127.0.0.1:8000/validateplayer");
        var p = new PlayerValidateEntity();
        p.setName("456");
        p.setPassword("pass1");
        var r = so.validatePlayer(p);
        var name = so.getPlayerFromJson(r);
        System.out.println(name.toString());
    }
}
