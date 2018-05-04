package beevy.backend.api;

import beevy.backend.converter.EventEntityToResourceConverter;
import beevy.backend.converter.EventResourceToEntityConverter;
import beevy.backend.model.Event;
import beevy.backend.repositories.EventRepository;
import com.beevy.api.EventApi;
import com.beevy.model.EventResource;
import com.beevy.model.JoinEventDataResource;
import com.google.common.collect.Lists;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
    private EventRepository repository;

    private EventResourceToEntityConverter eventResourceToEntityConverter = new EventResourceToEntityConverter();
    private EventEntityToResourceConverter eventEntityToResourceConverter = new EventEntityToResourceConverter();

    @Override
    @CrossOrigin
    public ResponseEntity<Void> createEvent(@ApiParam(value = "Event Object") @Valid @RequestBody EventResource body) {
        final Event newEvent = eventResourceToEntityConverter.toEntity(body);
        repository.save(newEvent);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<EventResource>> getEvents() {
        if(repository.findAll() == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<Event> existingEvents = repository.findAll();
        List<EventResource> events = new ArrayList<>();
        existingEvents.forEach(event -> {
            events.add(eventEntityToResourceConverter.toResource(event));
        });
        return new ResponseEntity<>(reverseEventList(events), HttpStatus.OK);
    }

    @Override
    @CrossOrigin
    public ResponseEntity<Void> joinEvent(@ApiParam(value = "Join Event Data"  )  @Valid @RequestBody JoinEventDataResource body) {
        Event event= repository.findByEventID(body.getEventID());
        addMemberToEvent(event, body.getUserID());
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    private void addMemberToEvent(Event event, String userID) {
        List<String> registeredMembers = event.getRegisteredMembers();
        registeredMembers.add(userID);
        event.setRegisteredMembers(registeredMembers);
        event.setCurrentMemberCount(event.getCurrentMemberCount() +1);
        repository.save(event);
    }


    private List<EventResource> reverseEventList(List<EventResource> oldList) {
        return Lists.reverse(oldList);
    }

}
