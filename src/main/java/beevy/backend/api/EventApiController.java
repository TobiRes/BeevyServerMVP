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
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

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

    @Autowired
    private JavaMailSender sender;

    private EventResourceToEntityConverter eventResourceToEntityConverter = new EventResourceToEntityConverter();
    private EventEntityToResourceConverter eventEntityToResourceConverter = new EventEntityToResourceConverter();

    @Override
    @CrossOrigin
    public ResponseEntity<Void> deleteEvent(@ApiParam(value = "Delete Event Object") @Valid @RequestBody DeleteEventDTOResource body) {

        User user = userRepository.findByUserID(body.getUserID());
        Event event = eventRepository.findByEventID(body.getEventID());

        //Check if User is admin
        if (user == null || event == null || !userIsAdminAndAllowedToDelete(body, user.getToken(), event.getAdmin().getUserID())) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        if (!deleteEventInUserCreatedEvents(user, body.getEventID())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        deleteEventFromAllJoinedMembers(event.getRegisteredMembers(), body.getEventID(), body.getUserID());
        deleteComments(event.getBaseComments());
        eventRepository.delete(body.getEventID());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private void deleteComments(List<String> baseComments) {
        if (baseComments != null) {
            baseComments.forEach(comment -> {
                commentRepository.delete(comment);
            });
        }
    }

    private void deleteEventFromAllJoinedMembers(List<String> registeredMembers, String eventID, String adminID) {
        registeredMembers.forEach(member -> {
            if (!member.equals(adminID)) {
                User joinedMember = userRepository.findByUserID(member);
                List<String> joinedEvents = joinedMember.getJoinedEvents();
                joinedEvents.remove(eventID);
                joinedMember.setJoinedEvents(joinedEvents);
                userRepository.save(joinedMember);
            }
        });
    }

    private boolean deleteEventInUserCreatedEvents(User user, String eventID) {
        List<String> createdEvents = user.getCreatedEvents();
        if (createdEvents.remove(eventID)) {
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
        if (userAllowedToCreateEvent(body) && allEventDataCorrect(body)) {
            EventResource eventResource = createEventResourceWithID(body);
            final Event newEvent = eventResourceToEntityConverter.toEntity(eventResource);
            //This also saves the event
            addUserToRegisteredMembersOfEvent(newEvent, body.getAdmin().getUserID());
            addEventToCreatedEventsOfUser(newEvent.getEventID(), body.getAdmin().getUserID());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    private boolean allEventDataCorrect(EventResource body) {
        //TODO: ALWAYS KEEP CONSISTENT WITH FRONTEND
        if (notAllDataEntered(body)) {
            return false;
        }
        if (body.getTitle().length() > 22 || body.getTitle().length() < 3) {
            return false;
        }
        if (body.getSummary().length() > 42 || body.getSummary().length() < 10) {
            return false;
        }
        if (body.getDescription().length() > 500 || body.getDescription().length() < 15) {
            return false;
        }
        if (body.getAddress().getZip().toString().length() != 5) {
            return false;
        }
        if (body.getAddress().getCity().length() < 4 || body.getAddress().getCity().length() > 25) {
            return false;
        }
        if(tagsIncorrect(body.getTags())){
            return false;
        }
        if (body.getAddress().getStreet().length() < 5 || body.getAddress().getStreet().length() > 25) {
            return false;
        }
        if (body.getType() != EventResource.TypeEnum.ACTIVITY || body.getType() != EventResource.TypeEnum.HANGOUT || body.getType() != EventResource.TypeEnum.PROJECT) {
            return true;
        }
        return false;
    }

    private boolean tagsIncorrect(List<String> tags) {
        if(tags == null || tags.size() > 10){
            return false;
        } else {
            return tags.stream().anyMatch(tag -> tag == null || tag.length() < 2 || tag.length() > 15);
        }
    }

    private boolean notAllDataEntered(EventResource body) {
        return (body.getTitle() == null || body.getSummary() == null || body.getDescription() == null || body.getType() == null || body.getDate() == null || body.getAddress() == null || body.getAddress().getStreet() == null || body.getAddress().getCity() == null || body.getAddress().getZip() == null);
    }

    private EventResource createEventResourceWithID(EventResource body) {
        String eventID = UUID.randomUUID().toString();
        return new EventResource()
                .eventID(eventID)
                .admin(new MinimalUserResource()
                        .userID(body.getAdmin().getUserID())
                        .username(body.getAdmin().getUsername())
                        .avatar(body.getAdmin().getAvatar()))
                .title(body.getTitle())
                .summary(body.getSummary())
                .description(body.getDescription())
                .type(body.getType())
                .date(body.getDate())
                .tags(body.getTags())
                .endDate(body.getEndDate())
                .address(body.getAddress())
                .registeredMembers(body.getRegisteredMembers())
                .possibleMemberCount(body.getPossibleMemberCount())
                .currentMemberCount(body.getCurrentMemberCount());
    }

    private void addEventToCreatedEventsOfUser(String eventID, String userID) {
        User user = userRepository.findByUserID(userID);
        List<String> createdEvents = new ArrayList<>();
        if (user.getCreatedEvents() != null) {
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
        List<Event> existingEvents = eventRepository.findAll();
        if (existingEvents == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<EventResource> events = new ArrayList<>();
        existingEvents.forEach(event -> {
            events.add(eventEntityToResourceConverter.toResource(event));
        });
        return new ResponseEntity<>(sortEventList(events), HttpStatus.OK);
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
    public ResponseEntity<Void> leaveEvent(@ApiParam(value = "Join Event Data"  )  @Valid @RequestBody LeaveEventDTOResource body) {
        if(body.getEventID() == null || body.getToken() == null || body.getUserID() == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        User user = userRepository.findByUserID(body.getUserID());
        Event event = eventRepository.findByEventID(body.getEventID());

        if(user == null || event == null || !event.getRegisteredMembers().contains(user.getUserID()) || !body.getToken().equals(user.getToken())){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if(event.getAdmin().getUserID().equals(user.getUserID())){
            return deleteEvent(new DeleteEventDTOResource().eventID(body.getEventID()).userID(body.getUserID()).token(body.getToken()));
        }

        List<String> registeredMembers = event.getRegisteredMembers();
        List<String> joinedEventsOfUser = user.getJoinedEvents();
        int currentMemberCount = event.getCurrentMemberCount();
        registeredMembers.remove(user.getUserID());
        joinedEventsOfUser.remove(event.getEventID());
        currentMemberCount -= 1;
        event.setRegisteredMembers(registeredMembers);
        user.setJoinedEvents(joinedEventsOfUser);
        event.setCurrentMemberCount(currentMemberCount);

        userRepository.save(user);
        eventRepository.save(event);
        return new ResponseEntity<>(HttpStatus.OK);
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
            UserEventsResource userEventsResource = getJoinedAndCreatedEventsOfUser(user.getJoinedEvents(), user.getCreatedEvents());
            return new ResponseEntity<>(userEventsResource, HttpStatus.OK);
        }

    }

    private UserEventsResource getJoinedAndCreatedEventsOfUser(List<String> joinedEventIDs, List<String> createdEventIDs) {
        UserEventsResource userEventsResource = new UserEventsResource();
        if (joinedEventIDs != null) {
            List<EventResource> joinedEvents = findJoinedEvents(joinedEventIDs);
            List<EventResource> sortedJoinedEvents = sortEventsByDate(joinedEvents);
            userEventsResource.setJoinedEvents(sortedJoinedEvents);
        }
        if (createdEventIDs != null) {
            List<EventResource> createdEvents = findCreatedEvents(createdEventIDs);
            List<EventResource> sortedCreatedEvents = sortEventsByDate(createdEvents);
            userEventsResource.setCreatedEvents(sortedCreatedEvents);
        }
        return userEventsResource;
    }

    private List<EventResource> findCreatedEvents(List<String> createdEvents) {
        List<EventResource> allCreatedEvents = new ArrayList<>();
        if (createdEvents != null) {
            createdEvents.forEach(eventID -> {
                Event createdEvent = eventRepository.findByEventID(eventID);
                if (createdEvent != null) {
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
        if (user == null || event.getAdmin().getUserID() == body.getUserID() || userAlreadyJoinedEvent(event, user) || event.getCurrentMemberCount().equals(event.getPossibleMemberCount())) {
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

    private boolean userAllowedToCreateEvent(EventResource body) {
        User user = userRepository.findByUserID(body.getAdmin().getUserID());
        if (user == null) {
            return false;
        }
        if (user.getToken().equals(body.getAdmin().getToken()) && user.getUsername().equals(body.getAdmin().getUsername()) && user.getUserID().equals(body.getAdmin().getUserID())) {
            return true;
        }
        return false;
    }

    private List<EventResource> sortEventList(List<EventResource> unsortedListWithAllEvents) {
        List<EventResource> oldEventsRemoved = getEventsNotInThePast(unsortedListWithAllEvents);
        List<EventResource> sortedEventList = sortEventsByDate(oldEventsRemoved);
        return sortedEventList;
    }

    private List<EventResource> sortEventsByDate(List<EventResource> unsortedList) {
        List<EventResource> newEventList = unsortedList.stream().collect(Collectors.toList());
        Collections.sort(newEventList, new Comparator<EventResource>() {
            public int compare(EventResource event1, EventResource event2) {
                if (event1.getDate() == null || event2.getDate() == null)
                    return 0;
                Date eventDate1 = getDateFromISOSTring(event1.getDate());
                Date eventDate2 = getDateFromISOSTring(event2.getDate());
                return eventDate1.compareTo(eventDate2);
            }
        });
        return newEventList;
    }

    private List<EventResource> getEventsNotInThePast(List<EventResource> allEvents) {
        //Returning true adds the event to the list
        List<EventResource> filteredList = allEvents.stream().filter(eventResource -> {
            try {
                Date eventDate = getDateFromISOSTring(eventResource.getDate());
                //0 if same Date, -1 if before, 1 if after
                return eventDate.compareTo(new Date()) > -1;
            } catch (Exception e) {
                return false;
            }
        }).collect(Collectors.toList());
        return filteredList;
    }

    private Date getDateFromISOSTring(String ISODate) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_DATE_TIME;
        OffsetDateTime offsetDateTime = OffsetDateTime.parse(ISODate, dateTimeFormatter);
        Date formattedDate = Date.from(Instant.from(offsetDateTime));
        return formattedDate;
    }

    @Override
    @CrossOrigin
    public ResponseEntity<Void> reportEvent(@ApiParam(value = "Report Event Data"  )  @Valid @RequestBody ReportDTOResource body) {
        if(body.getEventID() == null || body.getReason() == null || body.getToken() == null || body.getUserID() == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        User user = userRepository.findByUserID(body.getUserID());
        Event reportedEvent = eventRepository.findByEventID(body.getEventID());
        if(user == null || reportedEvent == null || !user.getToken().equals(body.getToken())){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        sendMail(user.getUsername(),"tobias.reski@gmail.com", body.getReason(), body.getEventID(), reportedEvent.getTitle(), reportedEvent.getSummary(), reportedEvent.getDescription());
        sendMail(user.getUsername(),"clara.deitmar@gmail.com", body.getReason(), body.getEventID(), reportedEvent.getTitle(), reportedEvent.getSummary(), reportedEvent.getDescription());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private void sendMail(String reportingUser, String mailAddress, String reportReason, String eventID, String eventTitle, String eventSummary, String eventDescription){
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        try {
            helper.setTo(mailAddress);
            helper.setText("Der User "
                    + reportingUser +" hat das Event mit dem Titel:<br><br>"
                    + eventTitle + "<br><br>Der Beschreibung: <br><br>'"
                    + eventDescription + "'<br><br> Und der Zusammenfassung: <br><br>'"
                    + eventSummary + "'<br><br> Gemeldet. Als Grund gab er an:<br><br><b>'"
                    + reportReason + "'</b><br><br>"
                    + "EventID: "
                    + eventID, true);
            helper.setSubject("Event " + eventTitle + " wurde gemeldet.");
            sender.send(message);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
