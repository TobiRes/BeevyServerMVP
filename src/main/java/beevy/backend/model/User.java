package beevy.backend.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@Builder
public class User {

    @Id
    private String userID;
    private String username;
    private String mail;
    private String token;
    private String tempAccessToken;

}