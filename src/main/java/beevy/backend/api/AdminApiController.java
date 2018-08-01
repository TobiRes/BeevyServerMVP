package beevy.backend.api;

import beevy.backend.model.Comment;
import beevy.backend.model.Event;
import beevy.backend.model.User;
import beevy.backend.repositories.CommentRepository;
import beevy.backend.repositories.EventRepository;
import beevy.backend.repositories.UserRepository;
import com.beevy.api.AdminApi;
import com.beevy.model.AdminCommentDeleteResource;
import com.beevy.model.AdminEventDeleteResource;
import com.beevy.model.AdminUserDeleteResource;
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
@CrossOrigin
public class AdminApiController implements AdminApi {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public ResponseEntity<Void> adminDeleteEvent(@ApiParam(value = "Admin Event Data"  )  @Valid @RequestBody AdminEventDeleteResource body) {
        if(!body.getAdminToken().equals("13ghJWEz!!")){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        String eventID = body.getEventID();
        Event eventToBeDeleted = eventRepository.findByEventID(eventID);

        String adminID = eventToBeDeleted.getAdmin().getUserID();
        User admin = userRepository.findByUserID(adminID);
        deleteEventInCreatedEventsOfAdmin(admin, eventID);

        List<String> registeredMembers = eventToBeDeleted.getRegisteredMembers();
        deleteEventInJoinedEventsOfMembers(registeredMembers, eventID, adminID);

        deleteEventComments(eventID);

        eventRepository.delete(eventID);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    private void deleteEventComments(String eventID) {
        commentRepository.deleteAllByEventID(eventID);
    }

    private void deleteEventInCreatedEventsOfAdmin(User admin, String eventID) {
        List<String> createdEvents = admin.getCreatedEvents();
        List<String> newCreatedEventsList = new ArrayList<>();
        createdEvents.forEach(createdEvent -> {
            if(!createdEvent.equals(eventID)){
                newCreatedEventsList.add(createdEvent);
            }
        });
        admin.setCreatedEvents(newCreatedEventsList);
        userRepository.save(admin);
    }

    private void deleteEventInJoinedEventsOfMembers(List<String> registeredMembers, String eventID, String adminID) {
        List<User> joinedUsers = new ArrayList<>();
        registeredMembers.forEach(registeredMember -> {
            if(!registeredMember.equals(adminID)){
                User joinedUser = userRepository.findByUserID(registeredMember);
                joinedUsers.add(joinedUser);
            }
        });
        joinedUsers.forEach(user -> {
            List<String> joinedEvents = user.getJoinedEvents();
            List<String> newJoinedEventsList = new ArrayList<>();
            joinedEvents.forEach(joinedEvent -> {
                if(!joinedEvent.equals(eventID)){
                    newJoinedEventsList.add(joinedEvent);
                }
            });
            user.setJoinedEvents(newJoinedEventsList);
            userRepository.save(user);
        });
    }


    @Override
    public ResponseEntity<Void> adminDeleteUser(@ApiParam(value = "User Delete Data"  )  @Valid @RequestBody AdminUserDeleteResource body) {
        if(!body.getAdminToken().equals("13ghJWEz!!")){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        User userToBeDeleted = userRepository.findByUserID(body.getUserID());

        List<String> joinedEvents = userToBeDeleted.getJoinedEvents();
        List<String> createdEvents = userToBeDeleted.getCreatedEvents();

        if(joinedEvents != null){
            deleteUserInRegisteredMembersOfEvents(joinedEvents, body.getUserID());
        }

        if(createdEvents != null){
            deleteEventAndAllItsDependencies(createdEvents, body.getUserID());
        }

        userRepository.delete(body.getUserID());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    private void deleteUserInRegisteredMembersOfEvents(List<String> joinedEvents, String userID) {
        joinedEvents.forEach(joinedEvent -> {
            Event joinedEventEntity = eventRepository.findByEventID(joinedEvent);
            List<String> registeredMemberList = joinedEventEntity.getRegisteredMembers();
            List<String> newRegisteredMemberList = new ArrayList<>();
            registeredMemberList.forEach(member -> {
                if(!member.equals(userID)){
                    newRegisteredMemberList.add(member);
                }
            });
            joinedEventEntity.setRegisteredMembers(newRegisteredMemberList);
            int currentMemberCount = joinedEventEntity.getCurrentMemberCount() - 1;
            joinedEventEntity.setCurrentMemberCount(currentMemberCount);
            eventRepository.save(joinedEventEntity);
        });
    }

    private void deleteEventAndAllItsDependencies(List<String> createdEvents, String adminID) {
        createdEvents.forEach(createdEvent -> {
            Event eventToBeDeleted = eventRepository.findByEventID(createdEvent);
            deleteEventInJoinedEventsOfMembers(eventToBeDeleted.getRegisteredMembers(), createdEvent, adminID);
            deleteEventComments(createdEvent);
            eventRepository.delete(createdEvent);
        });
    }

    @Override
    public ResponseEntity<Void> adminDeleteComment(@ApiParam(value = "Comment Data"  )  @Valid @RequestBody AdminCommentDeleteResource body) {
        if(!body.getAdminToken().equals("13ghJWEz!!")){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        Comment comment = commentRepository.findByCommentID(body.getCommentID());
        if(comment != null){
            commentRepository.delete(comment);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        List<Comment> commentsOfEvent = commentRepository.findAllByEventID(body.getEventID());
        commentsOfEvent.forEach(singleComment -> {
            if(singleComment.getComments() != null){
                List<Comment> secondLevelComments = singleComment.getComments();
                List<Comment> newSecondLevelComments = new ArrayList<>();
                secondLevelComments.forEach(secondLevelComment -> {
                    if(!secondLevelComment.getCommentID().equals(body.getCommentID())){
                        newSecondLevelComments.add(secondLevelComment);
                    }
                });
                singleComment.setComments(newSecondLevelComments);
                commentRepository.save(singleComment);
            }
        });

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
