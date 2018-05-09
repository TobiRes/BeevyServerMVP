package beevy.backend.api;

import beevy.backend.converter.UserResourceToEntityConverter;
import beevy.backend.model.User;
import beevy.backend.repositories.UserRepository;
import com.beevy.api.UserApi;
import com.beevy.model.UserResource;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

@EnableAutoConfiguration
@RestController
@AllArgsConstructor
public class UserApiController implements UserApi {

    @Autowired
    private UserRepository repository;

    private UserResourceToEntityConverter userResourceToEntityConverter = new UserResourceToEntityConverter();

    @Override
    @CrossOrigin
    public ResponseEntity<Void> createUser(@ApiParam(value = "User Object") @Valid @RequestBody UserResource body) {
        if (!allRequiredDataAvailable(body)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        final User user = repository.findByUserID(body.getUserID());
        if (user == null) {
            generateTokenAndSaveUser(body);
        } else {
            if (!allRequiredDataAvailable(user) || user.getToken() == null) {
                generateTokenAndSaveUser(body);
            }
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @Override
    @CrossOrigin
    public ResponseEntity<String> getUserToken(@PathVariable("username") final String username, @PathVariable("userID") final String userID) {
        final User user = repository.findByUserID(userID);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            if (user.getToken() == null) {
                generateTokenAndSaveUser(user);
            }
        }
        //Not pretty, but works.
        return new ResponseEntity<>("{\"token\":\"" + user.getToken() + "\"}", HttpStatus.OK);
    }

    private boolean allRequiredDataAvailable(UserResource userResource) {
        return (userResource.getMail() != null && userResource.getUsername() != null && userResource.getUserID() != null);
    }

    private boolean allRequiredDataAvailable(User userResource) {
        return (userResource.getMail() != null && userResource.getUsername() != null && userResource.getUserID() != null);
    }

    private void generateTokenAndSaveUser(UserResource body) {
        final String securityToken = UUID.randomUUID().toString().replace("-", "");
        body.setToken(securityToken);
        repository.save(userResourceToEntityConverter.toEntity(body));
    }

    private void generateTokenAndSaveUser(User body) {
        final String securityToken = UUID.randomUUID().toString().replace("-", "");
        body.setToken(securityToken);
        repository.save(body);
    }
}
