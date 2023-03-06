package org.example.connection.score;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.okhttp.ResponseBody;
import lombok.Getter;
import lombok.Setter;
import org.example.connection.jsonize.Jsonize;
import org.example.connection.interfaces.*;
import org.example.score.ScoreEntityCreate;
import org.example.score.ScoreEntityDisplay;
import org.example.score.AnswerStats;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;

public class ScoreOperations implements GetResponseBody, 
                                        DeserializeEntries<ScoreEntityDisplay> {

    @Getter
    @Setter
    String URL; // Substitute with yours


    /**
     * @return winrate
     * @throws IOException
     */
    public int getWinRate() throws IOException {
        var body = getResponseBody(URL);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(body.byteStream(), Integer.class);
    }

    /**
     * Returns score story
     * @return scores
     * @throws IOException
     */
    public List<? extends ScoreEntityDisplay> getScoreStory() throws IOException {
        var body = getResponseBody(URL);
        var scores = loadList(body, ScoreEntityDisplay.class);
        scores.sort(Comparator.comparing((ScoreEntityDisplay e) -> e.getPlayedOn()));
        return scores;
    }

    /**
     * Sends stats
     * @param stats AnswerStats
     * @return response
     * @throws IOException
     */
    public ResponseBody scoreSend(AnswerStats stats, String mode) throws IOException {

        String json = new Jsonize<ScoreEntityCreate>().jsonize(stats.toScoreEntityCreate(mode));
        return PostRequest.post(URL, json);
    }
    
}
