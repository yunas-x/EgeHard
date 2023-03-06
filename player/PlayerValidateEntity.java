package org.example.player;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.security.NoSuchAlgorithmException;


@NoArgsConstructor
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlayerValidateEntity implements HashPassword {

    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    private String name;

    @Getter
    private String password;


    /**
     * Hashes password
     * @param password
     * @throws NoSuchAlgorithmException
     */
    public void setPassword(String password) throws NoSuchAlgorithmException {
        this.password = hashPassword(password);
    }

}
