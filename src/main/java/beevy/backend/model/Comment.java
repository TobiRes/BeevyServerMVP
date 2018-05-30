package beevy.backend.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.List;

@Builder
@Getter
@Setter
public class Comment {

    @Id
    private String commentID;
    private String eventID;
    private String commentAuthor;
    private String commentBody;
    private String commentTime;
    private List<Comment> comments;
}
