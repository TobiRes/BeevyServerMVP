package beevy.backend.api;

import beevy.backend.converter.EventEntityToResourceConverter;
import beevy.backend.converter.EventResourceToEntityConverter;
import beevy.backend.model.Event;
import beevy.backend.model.User;
import beevy.backend.repositories.EventRepository;
import beevy.backend.repositories.UserRepository;
import com.beevy.api.EventApi;
import com.beevy.model.EventResource;
import com.beevy.model.JoinEventDataResource;
import com.beevy.model.UserEventsResource;
import com.google.common.collect.Lists;
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
import java.util.ArrayList;
import java.util.List;

@EnableAutoConfiguration
@RestController
@AllArgsConstructor
public class EventApiController implements EventApi {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;

    private EventResourceToEntityConverter eventResourceToEntityConverter = new EventResourceToEntityConverter();
    private EventEntityToResourceConverter eventEntityToResourceConverter = new EventEntityToResourceConverter();

    @Override
    @CrossOrigin
    public ResponseEntity<Void> createEvent(@ApiParam(value = "Event Object") @Valid @RequestBody EventResource body) {
        if (checkIfUserIsAllowedToCreateEvent(body)) {
            //TODO: Event Validierung
            final Event newEvent = eventResourceToEntityConverter.toEntity(body);
            eventRepository.save(newEvent);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @Override
    @CrossOrigin
    public ResponseEntity<List<EventResource>> getEvents() {
        if (eventRepository.findAll() == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<Event> existingEvents = eventRepository.findAll();
        List<EventResource> events = new ArrayList<>();
        existingEvents.forEach(event -> {
            events.add(eventEntityToResourceConverter.toResource(event));
        });
        return new ResponseEntity<>(reverseEventList(events), HttpStatus.OK);
    }

    @Override
    @CrossOrigin
    public ResponseEntity<Void> joinEvent(@ApiParam(value = "Join Event Data") @Valid @RequestBody JoinEventDataResource body) {
        Event event = eventRepository.findByEventID(body.getEventID());
        if (event != null && checkIfUserIsAllowedToJoinEvent(event, body)) {
            addMemberToEvent(event, body.getUserID());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Override
    @CrossOrigin
    public ResponseEntity<UserEventsResource> getUserEvents(@PathVariable("userID") String userID, @ApiParam(value = "tempAccessToken", required = true) @PathVariable("tempAccessToken") String tempAccessToken) {
        final User user = userRepository.findByUserID(userID);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else if (!user.getTempAccessToken().equals(tempAccessToken)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } else {
            user.setTempAccessToken(null);
            userRepository.save(user);
            List<EventResource> joinedEvents = findJoinedEvents(user.getJoinedEvents());
            List<EventResource> createdEvents = findCreatedEvents(user.getCreatedEvents());
            UserEventsResource userEventsResource = new UserEventsResource();
            userEventsResource.setCreatedEvents(createdEvents);
            userEventsResource.setJoinedEvents(joinedEvents);
            return new ResponseEntity<>(userEventsResource, HttpStatus.OK);
        }

    }

    private List<EventResource> findCreatedEvents(List<String> createdEvents) {
        List<EventResource> allCreatedEvents = new ArrayList<>();
        if (createdEvents != null) {
            createdEvents.forEach(eventID -> {
                Event createdEvent = eventRepository.findByEventID(eventID);
                allCreatedEvents.add(eventEntityToResourceConverter.toResource(createdEvent));
            });
        }
        return allCreatedEvents;
    }

    private List<EventResource> findJoinedEvents(List<String> joinedEvents) {
        List<EventResource> allJoinedEvents = new ArrayList<>();
        joinedEvents.forEach(eventID -> {
            Event joinedEvent = eventRepository.findByEventID(eventID);
            allJoinedEvents.add(eventEntityToResourceConverter.toResource(joinedEvent));
        });
        return allJoinedEvents;
    }

    private boolean checkIfUserIsAllowedToJoinEvent(Event event, JoinEventDataResource body) {
        User user = userRepository.findByUserID(body.getUserID());
        if (user == null || event.getAdmin() == body.getUserID() || userAlreadyJoinedEvent(event, user)) {
            return false;
        }
        return true;
    }

    private boolean userAlreadyJoinedEvent(Event event, User user) {
        if (event.getRegisteredMembers() == null) {
            return false;
        }
        if (event.getRegisteredMembers().contains(user.getUserID())) {
            return true;
        }
        return false;
    }

    private void addMemberToEvent(Event event, String userID) {
        addEventToJoinedEventsOfUser(userID, event.getEventID());
        addUserToRegisteredMembersOfEvent(event, userID);
    }

    private void addUserToRegisteredMembersOfEvent(Event event, String userID) {
        List<String> registeredMembers = new ArrayList<>();
        if (event.getRegisteredMembers() != null) {
            registeredMembers = event.getRegisteredMembers();
        }
        registeredMembers.add(userID);
        event.setRegisteredMembers(registeredMembers);
        if (event.getCurrentMemberCount() == null) {
            event.setCurrentMemberCount(1);
        } else {
            event.setCurrentMemberCount(event.getCurrentMemberCount() + 1);
        }
        eventRepository.save(event);
    }

    private void addEventToJoinedEventsOfUser(String userID, String eventID) {
        User user = userRepository.findByUserID(userID);
        List<String> joinedEvents = new ArrayList<>();
        if (user.getJoinedEvents() != null) {
            joinedEvents = user.getJoinedEvents();
        }
        joinedEvents.add(eventID);
        user.setJoinedEvents(joinedEvents);
        userRepository.save(user);
    }

    private boolean checkIfUserIsAllowedToCreateEvent(EventResource body) {
        User user = userRepository.findByUserID(body.getAdmin().getUserID());
        if (user == null) {
            return false;
        }
        if (user.getToken().equals(body.getAdmin().getToken()) && user.getUsername().equals(body.getAdmin().getUsername()) && user.getUserID().equals(body.getAdmin().getUserID())) {
            return true;
        }
        return false;
    }

    private List<EventResource> reverseEventList(List<EventResource> oldList) {
        return Lists.reverse(oldList);
    }
}
