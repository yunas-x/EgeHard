package org.example.player;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PlayerEntityCreate extends PlayerValidateEntity {

    @Getter
    @Setter
    private String email;

}
