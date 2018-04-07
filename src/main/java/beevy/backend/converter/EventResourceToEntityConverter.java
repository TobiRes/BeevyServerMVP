package beevy.backend.converter;

import beevy.backend.model.Event;
import com.beevy.model.EventResource;
import org.springframework.stereotype.Component;

@Component
public class EventResourceToEntityConverter {

    public Event toEntity(final EventResource source) {
        Event event = new Event();
       return new Event()
               .userID(source.getUserID())
               .userSecret(source.getUserSecret())
               .admin(source.getAdmin())
               .title(source.getTitle())
               .summary(source.getSummary())
               .description(source.getDescription())
               .type(source.getType())
               .date(source.getDate())
               .endDate(source.getEndDate())
               .address(source.getAddress())
               .registeredMembers(source.getRegisteredMembers())
               .possibleMemberCount(source.getPossibleMemberCount())
               .currentMemberCount(source.getCurrentMemberCount());
    }
}
