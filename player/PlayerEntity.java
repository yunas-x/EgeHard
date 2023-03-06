package org.example.player;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;


@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class PlayerEntity {

    @Getter(AccessLevel.PUBLIC)
    private int id;

    @Getter(AccessLevel.PUBLIC)
    private String name;

    @Getter(AccessLevel.PUBLIC)
    private Date registeredOn;

    /**
     * Handles server errors
     * @return error code
     */
    public PlayerStatus isPlayerValid() {
        if (this.id == -1)
            return PlayerStatus.NAME_DO_NOT_EXIST;
        if (this.id == -2)
            return PlayerStatus.WRONG_PASS;
        if (this.id == -3)
            return PlayerStatus.NAME_IS_IN_USE;
        if (this.id == -4)
            return PlayerStatus.MAIL_ALREADY_USED;
        return PlayerStatus.OK;
    }

}

