package beevy.backend.api;

import beevy.backend.converter.UserResourceToEntityConverter;
import beevy.backend.model.User;
import beevy.backend.repositories.UserRepository;
import com.beevy.api.UserApi;
import com.beevy.model.UserResource;
import com.beevy.model.UserSecurityResource;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.internet.MimeMessage;
import javax.validation.Valid;
import java.util.Random;
import java.util.UUID;

@EnableAutoConfiguration
@RestController
@AllArgsConstructor
public class UserApiController implements UserApi {

    @Autowired
    private UserRepository repository;

    @Autowired
    JavaMailSender sender;

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
    public ResponseEntity<Void> registerUser(@ApiParam(value = "Security Object") @Valid @RequestBody UserResource body) {
        if (allRequiredDataAvailable(body) && mailValid(body.getMail())) {
            final User user = repository.findByUserID(body.getUserID());
            if(user != null){
                //TODO: Handle case where user is already registered
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            final String registerToken = generateRandomString();
            User newUser = userResourceToEntityConverter.toEntity(body);
            newUser.setTempAccessToken(registerToken);
            if (!sendMail(body.getMail(), body.getUsername(), registerToken)) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
            repository.save(newUser);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    private Boolean sendMail(String mail, String username, String registerToken) {
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        try {
            helper.setTo(mail);
            helper.setText("Hallo " + username + "<br><br> Hier der Registrierungs-Code, denn du in der Beevy-App eingeben musst um deine Registrierung abzuschließen: <br><br>" + "<b>" + registerToken + "</b>" + "<br><br> Viel Spaß! <br><br> Dein Beevy Team", true);
            helper.setSubject("Beevy App Registrierung");
            sender.send(message);
            return true;
        } catch (Exception ex) {
            System.out.println(ex);
            return false;
        }
    }

    private String generateRandomString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 5) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
    }

    private boolean mailValid(String mail) {
        return (mail.toLowerCase().contains("@stud.hs-offenburg.de") || mail.toLowerCase().contains("@hs-offenburg.de"));
    }

    @Override
    @CrossOrigin
    public ResponseEntity<Void> setTempAccessTokenForUser(@ApiParam(value = "Security Object") @Valid @RequestBody UserSecurityResource body) {
        final User user = repository.findByUserID(body.getUserID());
        if (user == null || !allRequiredDataAvailable(user) || !(user.getUsername().equals(body.getUsername()))) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            user.setTempAccessToken(body.getTempToken());
            repository.save(user);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @Override
    @CrossOrigin
    public ResponseEntity<String> getUserToken(@PathVariable("username") final String username, @PathVariable("userID") final String userID, @PathVariable("tempAccessToken") final String tempAccessToken) {
        final User user = repository.findByUserID(userID);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else if (user.getTempAccessToken() == null || !user.getTempAccessToken().equals(tempAccessToken)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } else {
            if (user.getToken() == null) {
                generateTokenAndSaveUser(user);
            }
            user.setTempAccessToken(null);
            repository.save(user);
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
