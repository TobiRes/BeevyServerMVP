package beevy.backend.converter;

import beevy.backend.model.User;
import com.beevy.model.UserResource;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserResourceToEntityConverter {

    public User toEntity(final UserResource source) {
        return User.builder()
                .userID(source.getUserID())
                .username(source.getUsername())
                .token(source.getToken())
                .mail(source.getMail())
                .build();
    }
}
