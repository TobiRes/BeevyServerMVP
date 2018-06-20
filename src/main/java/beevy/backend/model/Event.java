package beevy.backend.model;

import com.beevy.model.AddressResource;
import com.beevy.model.EventResource;
import com.beevy.model.MinimalUserResource;
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
    private String eventID;
    private MinimalUserResource admin;
    private String title;
    private String summary;
    private String description;
    private EventResource.TypeEnum type;
    private String date;
    private String endDate;
    private AddressResource address;
    private List<String> registeredMembers;
    private Integer possibleMemberCount;
    private Integer currentMemberCount;
    private List<String> baseComments;

}

