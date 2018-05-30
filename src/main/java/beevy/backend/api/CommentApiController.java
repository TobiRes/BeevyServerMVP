package beevy.backend.api;

import beevy.backend.model.Event;
import beevy.backend.model.User;
import beevy.backend.repositories.CommentRepository;
import beevy.backend.repositories.EventRepository;
import beevy.backend.repositories.UserRepository;
import com.beevy.api.CommentApi;
import com.beevy.model.CommentDTOResource;
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
import java.util.List;

@EnableAutoConfiguration
@RestController
@AllArgsConstructor
@CrossOrigin
public class CommentApiController implements CommentApi {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public ResponseEntity<Void> addComment(@ApiParam(value = "Comment Data"  )  @Valid @RequestBody CommentDTOResource body) {
        if(notAllRequiredDataAvailableAndValidFormat(body)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }


        //TODO: Implement
        //Check if event already has comments, or if replied to is not set
        //If not, add this comment to event
        //Else add comment to list of repliedTo Comments

        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    private boolean notAllRequiredDataAvailableAndValidFormat(CommentDTOResource body) {
        final User user = userRepository.findByUserID(body.getUserID());
        final Event event = eventRepository.findByEventID(body.getEventID());

        return (notAllCommentDataAvailable(body)) 
                || (user.getToken() != body.getUserToken())
                || (event == null)
                || userNotAllowedToComment(event.getRegisteredMembers(), event.getAdmin(), user.getUserID());
    }

    private boolean userNotAllowedToComment(List<String> registeredMembers, String admin, String userID) {
        return !(registeredMembers.contains(userID) || admin.equals(userID));
    }

    private boolean notAllCommentDataAvailable(CommentDTOResource comment) {
        return (comment.getUserID() == null
                || comment.getUserToken() == null
                || comment.getCommentBody() == null
                || comment.getCommentTime() == null
                || comment.getEventID() == null
                || comment.getCommentBody() == null
                || comment.getRepliedTo() == null);
    }
}
