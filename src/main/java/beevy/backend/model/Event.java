package beevy.backend.model;

import com.beevy.model.AddressResource;
import com.beevy.model.EventResource;
import com.beevy.model.UserResource;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.List;

@Builder
@Getter
@Setter
public class Event {

    @Id
    private String userID;
    private String userSecret;
    private UserResource admin;
    private String title;
    private String summary;
    private String description;
    private EventResource.TypeEnum type;
    private String date;
    private String endDate;
    private AddressResource address;
    private List<UserResource> registeredMembers;
    private Integer possibleMemberCount;
    private Integer currentMemberCount;

}

