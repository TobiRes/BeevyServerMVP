package beevy.backend.api;

import beevy.backend.converter.EventEntityToResourceConverter;
import beevy.backend.converter.EventResourceToEntityConverter;
import beevy.backend.model.Event;
import beevy.backend.repositories.EventRepository;
import com.beevy.api.EventApi;
import com.beevy.model.EventResource;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

}
