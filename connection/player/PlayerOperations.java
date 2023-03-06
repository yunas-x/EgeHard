package org.example.connection.player;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.okhttp.ResponseBody;
import lombok.Getter;
import lombok.Setter;
import org.example.connection.jsonize.Jsonize;
import org.example.connection.interfaces.*;
import org.example.player.PlayerEntity;
import org.example.player.PlayerEntityCreate;
import org.example.player.PlayerValidateEntity;

import java.io.IOException;

public class PlayerOperations implements GetResponseBody, DeserializeEntries<PlayerEntityCreate> {

    @Getter
    @Setter
    String URL; // Substitute with yours

    /**
     * Creating a player
     * @param player player entity to pass to db
     * @return Response body convertible to player
     * @throws IOException
     */
    public ResponseBody createPlayer(PlayerEntityCreate player) throws IOException {
        String json = new Jsonize<PlayerEntityCreate>().jsonize(player);
        return PostRequest.post(URL, json);
    }

    /**
     * Checks names and password
     * @param player player entity to validate in db
     * @return Response body convertible to player
     * @throws IOException
     */
    public ResponseBody validatePlayer(PlayerValidateEntity player)  throws IOException {
        String json = new Jsonize<PlayerValidateEntity>().jsonize(player);
        return PostRequest.post(URL, json);
    }

    /**
     * Deserializes Player from JSON. Check status afterwards
     * @param json string representing player
     * @return Player entity
     * @throws PlayerRequestException on bad request
     */
    public PlayerEntity getPlayerFromJson(ResponseBody json) throws PlayerRequestException {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(json.byteStream(), PlayerEntity.class);
        }
        catch (IOException e) {
            throw new PlayerRequestException(json.toString());
        }
    }


}

