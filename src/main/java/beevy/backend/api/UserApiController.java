package beevy.backend.api;

import beevy.backend.converter.UserResourceToEntityConverter;
import beevy.backend.model.Event;
import beevy.backend.model.User;
import beevy.backend.repositories.EventRepository;
import beevy.backend.repositories.UserRepository;
import com.beevy.api.UserApi;
import com.beevy.model.AvatarDTOResource;
import com.beevy.model.MinimalUserResource;
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
import java.util.*;

@EnableAutoConfiguration
@RestController
@AllArgsConstructor
public class UserApiController implements UserApi {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    JavaMailSender sender;

    private UserResourceToEntityConverter userResourceToEntityConverter = new UserResourceToEntityConverter();

    @Override
    @CrossOrigin
    public ResponseEntity<Void> registerUser(@ApiParam(value = "Security Object") @Valid @RequestBody UserResource body) {
        if (allRequiredDataAvailable(body) && mailValid(body.getMail())) {
            final User user = userRepository.findByUserID(body.getUserID());
            if (user != null && user.getToken() != null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            final String registerToken = generateRandomString();
            User newUser = userResourceToEntityConverter.toEntity(body);
            newUser.setTempAccessToken(registerToken);
            newUser.setCurrentAvatar("avatar_1");
            if (!sendMail(body.getMail(), body.getUsername(), registerToken)) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
            userRepository.save(newUser);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    private Boolean sendMail(String mail, String username, String registerToken) {
        //https://www.quickprogrammingtips.com/spring-boot/how-to-send-email-from-spring-boot-applications.html
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
        final User user = userRepository.findByUserID(body.getUserID());
        if (user == null || !allRequiredDataAvailable(user) || !(user.getUsername().equals(body.getUsername()) || !(user.getToken().equals(body.getToken())))) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            user.setTempAccessToken(body.getTempToken());
            userRepository.save(user);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @Override
    @CrossOrigin
    public ResponseEntity<String> getUserToken(@PathVariable("username") final String username, @PathVariable("userID") final String userID, @PathVariable("tempAccessToken") final String tempAccessToken) {
        final User user = userRepository.findByUserID(userID);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else if (user.getTempAccessToken() == null || !user.getTempAccessToken().equals(tempAccessToken)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } else {
            if (user.getToken() == null) {
                generateTokenAndSaveUser(user);
            }
            user.setTempAccessToken(null);
            userRepository.save(user);
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

    private void generateTokenAndSaveUser(User body) {
        final String securityToken = UUID.randomUUID().toString().replace("-", "");
        body.setToken(securityToken);
        userRepository.save(body);
    }

    @Override
    @CrossOrigin
    public ResponseEntity<Void> updateAvatar(@ApiParam(value = "AvatarDTO") @Valid @RequestBody AvatarDTOResource body) {
        User user = userRepository.findByUserID(body.getUserID());
        if (user == null || !user.getToken().equals(body.getToken()) || body.getAvatar() == null || !avatarIsValidFormat(body)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        user.setCurrentAvatar(body.getAvatar());
        userRepository.save(user);
        updateAvatarInEvents(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private void updateAvatarInEvents(User user) {
        List<String> createdEventsOfUser = user.getCreatedEvents();
        if (createdEventsOfUser != null) {
            createdEventsOfUser.forEach(eventID -> {
                //TODO reduce load
                Event createdEvent = eventRepository.findByEventID(eventID);
                MinimalUserResource eventAdmin = createdEvent.getAdmin();
                eventAdmin.setAvatar(user.getCurrentAvatar());
                createdEvent.setAdmin(eventAdmin);
                eventRepository.save(createdEvent);
            });
        }
    }

    private boolean avatarIsValidFormat(AvatarDTOResource body) {
        try {
            //Get the number after 'avatar_'
            final int i = new Scanner(body.getAvatar().substring(7)).useDelimiter("\\D+").nextInt();
            return body.getAvatar().toLowerCase().contains("avatar_") && i > 0 && i < 13;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
