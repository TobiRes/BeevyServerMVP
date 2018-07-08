package beevy.backend.converter;

import beevy.backend.model.Event;
import com.beevy.model.EventResource;
import com.beevy.model.MinimalUserResource;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class EventResourceToEntityConverter {

    public Event toEntity(final EventResource source) {
        return Event.builder()
                .eventID(source.getEventID())
                .admin(new MinimalUserResource()
                        .username(source.getAdmin().getUsername())
                        .userID(source.getAdmin().getUserID())
                        .avatar(source.getAdmin().getAvatar()))
                .title(source.getTitle())
                .summary(source.getSummary())
                .description(source.getDescription())
                .type(source.getType())
                .date(source.getDate())
                .endDate(source.getEndDate())
                .address(source.getAddress())
                .registeredMembers(source.getRegisteredMembers())
                .possibleMemberCount(source.getPossibleMemberCount())
                .currentMemberCount(source.getCurrentMemberCount())
                .build();
    }
}
