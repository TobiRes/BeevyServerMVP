package beevy.backend.api;

import beevy.backend.model.Comment;
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
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

        final User user = userRepository.findByUserID(body.getUserID());
        final Event event = eventRepository.findByEventID(body.getEventID());

        if(user == null || event == null || notAllRequiredDataAvailableOrNotValidFormat(body, user, event)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Comment newComment = buildComment(body, user.getUsername());
        if(body.getRepliedTo() == null || event.getBaseComments() == null){
            addCommentToBaseOfEvent(event, newComment);
        } else {
            Comment existingComment = commentRepository.findByCommentID(body.getRepliedTo());
            if(existingComment == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            addCommentToExistingComment(newComment, existingComment);
        }

        //TODO: Add comments to user?
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private void addCommentToExistingComment(Comment newComment, Comment existingComment) {
        List<Comment> commentsOfExistingComment = new ArrayList<>();

        if(existingComment.getComments() != null){
            commentsOfExistingComment = existingComment.getComments();
        }
        commentsOfExistingComment.add(newComment);
        existingComment.setComments(commentsOfExistingComment);
        commentRepository.save(existingComment);
    }

    private void addCommentToBaseOfEvent(Event event, Comment comment){
        List<String> commentList = new ArrayList<>();
        if(event.getBaseComments() != null){
            commentList = event.getBaseComments();
        }
        commentList.add(comment.getCommentID());
        event.setBaseComments(commentList);
        commentRepository.save(comment);
        eventRepository.save(event);
    }

    private Comment buildComment(CommentDTOResource body, String username) {
        UUID uuid = UUID.randomUUID();
        return Comment.builder()
                .commentAuthor(username)
                .commentBody(body.getCommentBody())
                .commentTime(body.getCommentTime())
                .commentID(uuid.toString())
                .eventID(body.getEventID())
                .build();
    }

    private boolean notAllRequiredDataAvailableOrNotValidFormat(CommentDTOResource body, User user, Event event) {
        return (notAllCommentDataAvailable(body))
                || !(user.getToken().equals(body.getUserToken()))
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
                || comment.getEventID() == null);
    }
}
