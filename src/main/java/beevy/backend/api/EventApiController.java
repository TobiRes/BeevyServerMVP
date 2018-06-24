package beevy.backend.api;

import beevy.backend.converter.EventEntityToResourceConverter;
import beevy.backend.converter.EventResourceToEntityConverter;
import beevy.backend.model.Event;
import beevy.backend.model.User;
import beevy.backend.repositories.CommentRepository;
import beevy.backend.repositories.EventRepository;
import beevy.backend.repositories.UserRepository;
import com.beevy.api.EventApi;
import com.beevy.model.*;
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
import java.util.UUID;

@EnableAutoConfiguration
@RestController
@AllArgsConstructor
public class EventApiController implements EventApi {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommentRepository commentRepository;

    private EventResourceToEntityConverter eventResourceToEntityConverter = new EventResourceToEntityConverter();
    private EventEntityToResourceConverter eventEntityToResourceConverter = new EventEntityToResourceConverter();

    @Override
    @CrossOrigin
    public ResponseEntity<Void> deleteEvent(@ApiParam(value = "Delete Event Object"  )  @Valid @RequestBody DeleteEventDTOResource body) {

        User user = userRepository.findByUserID(body.getUserID());
        Event event = eventRepository.findByEventID(body.getEventID());

        //Check if User is admin
        if(user == null || event == null || !userIsAdminAndAllowedToDelete(body, user.getToken(), event.getAdmin().getUserID())) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        if(!deleteEventInUserCreatedEvents(user, body.getEventID())){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        deleteEventFromAllJoinedMembers(event.getRegisteredMembers(), body.getEventID());
        deleteComments(event.getBaseComments());
        eventRepository.delete(body.getEventID());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private void deleteComments(List<String> baseComments) {
        if(baseComments != null){
            baseComments.forEach(comment -> {
                commentRepository.delete(comment);
            });
        }
    }

    private void deleteEventFromAllJoinedMembers(List<String> registeredMembers, String eventID) {
        registeredMembers.forEach(member -> {
            User joinedMember = userRepository.findByUserID(member);
            List<String> joinedEvents = joinedMember.getJoinedEvents();
            joinedEvents.remove(eventID);
            joinedMember.setJoinedEvents(joinedEvents);
            userRepository.save(joinedMember);
        });
    }

    private boolean deleteEventInUserCreatedEvents(User user, String eventID) {
        List<String> createdEvents = user.getCreatedEvents();
        if(createdEvents.remove(eventID)) {
            user.setCreatedEvents(createdEvents);
            userRepository.save(user);
            return true;
        }
        return false;
    }

    private boolean userIsAdminAndAllowedToDelete(DeleteEventDTOResource body, String token, String admin) {
        return (body.getToken().equals(token) && body.getUserID().equals(admin));
    }

    @Override
    @CrossOrigin
    public ResponseEntity<Void> createEvent(@ApiParam(value = "Event Object") @Valid @RequestBody EventResource body) {
        if (checkIfUserIsAllowedToCreateEvent(body)) {
            //TODO: Event Validierung
            EventResource eventResource = createEventResourceWithID(body);
            final Event newEvent = eventResourceToEntityConverter.toEntity(eventResource);
            //This also saves the event
            addUserToRegisteredMembersOfEvent(newEvent, body.getAdmin().getUserID());

            addEventToCreatedEventsOfUser(newEvent.getEventID(), body.getAdmin().getUserID());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    private EventResource createEventResourceWithID(EventResource body) {
        String eventID = UUID.randomUUID().toString();
        return new EventResource()
                .eventID(eventID)
                .admin(new MinimalUserResource().userID(body.getAdmin().getUserID()).username(body.getAdmin().getUsername()))
                .title(body.getTitle())
                .summary(body.getSummary())
                .description(body.getDescription())
                .type(body.getType())
                .date(body.getDate())
                .endDate(body.getEndDate())
                .address(body.getAddress())
                .registeredMembers(body.getRegisteredMembers())
                .possibleMemberCount(body.getPossibleMemberCount())
                .currentMemberCount(body.getCurrentMemberCount());
    }

    private void addEventToCreatedEventsOfUser(String eventID, String userID) {
        User user = userRepository.findByUserID(userID);
        List<String> createdEvents = new ArrayList<>();
        if(user.getCreatedEvents() != null) {
            createdEvents = user.getCreatedEvents();
            createdEvents.add(eventID);
        } else {
            createdEvents.add(eventID);
        }
        user.setCreatedEvents(createdEvents);
        userRepository.save(user);
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
            UserEventsResource userEventsResource = new UserEventsResource();
            if(user.getJoinedEvents() != null){
                List<EventResource> joinedEvents = findJoinedEvents(user.getJoinedEvents());
                userEventsResource.setJoinedEvents(joinedEvents);
            }
            if(user.getCreatedEvents() != null) {
                List<EventResource> createdEvents = findCreatedEvents(user.getCreatedEvents());
                userEventsResource.setCreatedEvents(createdEvents);
            }
            return new ResponseEntity<>(userEventsResource, HttpStatus.OK);
        }

    }

    private List<EventResource> findCreatedEvents(List<String> createdEvents) {
        List<EventResource> allCreatedEvents = new ArrayList<>();
        if (createdEvents != null) {
            createdEvents.forEach(eventID -> {
                Event createdEvent = eventRepository.findByEventID(eventID);
                if(createdEvent != null){
                    allCreatedEvents.add(eventEntityToResourceConverter.toResource(createdEvent));
                }
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
        if (user == null || event.getAdmin().getUserID() == body.getUserID() || userAlreadyJoinedEvent(event, user)) {
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
