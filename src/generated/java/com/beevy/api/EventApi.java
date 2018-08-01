/**
 * NOTE: This class is auto generated by the swagger code generator program (2.3.1).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package com.beevy.api;

import com.beevy.model.DeleteEventDTOResource;
import com.beevy.model.EventResource;
import com.beevy.model.JoinEventDataResource;
import com.beevy.model.LeaveEventDTOResource;
import com.beevy.model.ReportDTOResource;
import com.beevy.model.UserEventsResource;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-08-01T11:14:59.938+02:00")

@Api(value = "event", description = "the event API")
public interface EventApi {

    Logger log = LoggerFactory.getLogger(EventApi.class);

    default Optional<ObjectMapper> getObjectMapper() {
        return Optional.empty();
    }

    default Optional<HttpServletRequest> getRequest() {
        return Optional.empty();
    }

    default Optional<String> getAcceptHeader() {
        return getRequest().map(r -> r.getHeader("Accept"));
    }

    @ApiOperation(value = "Create an event", nickname = "createEvent", notes = "Create an event for Beevy", tags={ "event", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "Created event"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 405, message = "Failed to create event") })
    @RequestMapping(value = "/event/create",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    default ResponseEntity<Void> createEvent(@ApiParam(value = "Event Object"  )  @Valid @RequestBody EventResource body) {
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
        } else {
            log.warn("ObjectMapper or HttpServletRequest not configured in default EventApi interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }


    @ApiOperation(value = "Delete an event", nickname = "deleteEvent", notes = "Delete an event from Beevy", tags={ "event", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "Deleted event"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 405, message = "Failed to delete event") })
    @RequestMapping(value = "/event/delete",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    default ResponseEntity<Void> deleteEvent(@ApiParam(value = "Delete Event Object"  )  @Valid @RequestBody DeleteEventDTOResource body) {
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
        } else {
            log.warn("ObjectMapper or HttpServletRequest not configured in default EventApi interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }


    @ApiOperation(value = "Get all events that are available", nickname = "getEvents", notes = "Get Events", response = EventResource.class, responseContainer = "List", tags={ "event", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = EventResource.class, responseContainer = "List"),
        @ApiResponse(code = 400, message = "Failed to get events") })
    @RequestMapping(value = "/event",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    default ResponseEntity<List<EventResource>> getEvents() {
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
            if (getAcceptHeader().get().contains("application/json")) {
                try {
                    return new ResponseEntity<>(getObjectMapper().get().readValue("[ {  \"summary\" : \"summary\",  \"date\" : \"date\",  \"registeredMembers\" : [ \"registeredMembers\", \"registeredMembers\" ],  \"eventID\" : \"eventID\",  \"address\" : {    \"zip\" : 0,    \"city\" : \"city\",    \"street\" : \"street\"  },  \"currentMemberCount\" : 1,  \"possibleMemberCount\" : 6,  \"endDate\" : \"endDate\",  \"admin\" : {    \"avatar\" : \"avatar\",    \"userID\" : \"userID\",    \"username\" : \"username\",    \"token\" : \"token\"  },  \"description\" : \"description\",  \"title\" : \"title\",  \"type\" : \"project\",  \"tags\" : [ \"tags\", \"tags\" ]}, {  \"summary\" : \"summary\",  \"date\" : \"date\",  \"registeredMembers\" : [ \"registeredMembers\", \"registeredMembers\" ],  \"eventID\" : \"eventID\",  \"address\" : {    \"zip\" : 0,    \"city\" : \"city\",    \"street\" : \"street\"  },  \"currentMemberCount\" : 1,  \"possibleMemberCount\" : 6,  \"endDate\" : \"endDate\",  \"admin\" : {    \"avatar\" : \"avatar\",    \"userID\" : \"userID\",    \"username\" : \"username\",    \"token\" : \"token\"  },  \"description\" : \"description\",  \"title\" : \"title\",  \"type\" : \"project\",  \"tags\" : [ \"tags\", \"tags\" ]} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
                } catch (IOException e) {
                    log.error("Couldn't serialize response for content type application/json", e);
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
        } else {
            log.warn("ObjectMapper or HttpServletRequest not configured in default EventApi interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }


    @ApiOperation(value = "Return a users events", nickname = "getUserEvents", notes = "Get events of a user", response = UserEventsResource.class, tags={ "event", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = UserEventsResource.class),
        @ApiResponse(code = 405, message = "Failed to get events") })
    @RequestMapping(value = "/event/{userID}/{tempAccessToken}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    default ResponseEntity<UserEventsResource> getUserEvents(@ApiParam(value = "ID of a User",required=true) @PathVariable("userID") String userID,@ApiParam(value = "tempAccessToken",required=true) @PathVariable("tempAccessToken") String tempAccessToken) {
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
            if (getAcceptHeader().get().contains("application/json")) {
                try {
                    return new ResponseEntity<>(getObjectMapper().get().readValue("{  \"joinedEvents\" : [ {    \"summary\" : \"summary\",    \"date\" : \"date\",    \"registeredMembers\" : [ \"registeredMembers\", \"registeredMembers\" ],    \"eventID\" : \"eventID\",    \"address\" : {      \"zip\" : 0,      \"city\" : \"city\",      \"street\" : \"street\"    },    \"currentMemberCount\" : 1,    \"possibleMemberCount\" : 6,    \"endDate\" : \"endDate\",    \"admin\" : {      \"avatar\" : \"avatar\",      \"userID\" : \"userID\",      \"username\" : \"username\",      \"token\" : \"token\"    },    \"description\" : \"description\",    \"title\" : \"title\",    \"type\" : \"project\",    \"tags\" : [ \"tags\", \"tags\" ]  }, {    \"summary\" : \"summary\",    \"date\" : \"date\",    \"registeredMembers\" : [ \"registeredMembers\", \"registeredMembers\" ],    \"eventID\" : \"eventID\",    \"address\" : {      \"zip\" : 0,      \"city\" : \"city\",      \"street\" : \"street\"    },    \"currentMemberCount\" : 1,    \"possibleMemberCount\" : 6,    \"endDate\" : \"endDate\",    \"admin\" : {      \"avatar\" : \"avatar\",      \"userID\" : \"userID\",      \"username\" : \"username\",      \"token\" : \"token\"    },    \"description\" : \"description\",    \"title\" : \"title\",    \"type\" : \"project\",    \"tags\" : [ \"tags\", \"tags\" ]  } ],  \"createdEvents\" : [ {    \"summary\" : \"summary\",    \"date\" : \"date\",    \"registeredMembers\" : [ \"registeredMembers\", \"registeredMembers\" ],    \"eventID\" : \"eventID\",    \"address\" : {      \"zip\" : 0,      \"city\" : \"city\",      \"street\" : \"street\"    },    \"currentMemberCount\" : 1,    \"possibleMemberCount\" : 6,    \"endDate\" : \"endDate\",    \"admin\" : {      \"avatar\" : \"avatar\",      \"userID\" : \"userID\",      \"username\" : \"username\",      \"token\" : \"token\"    },    \"description\" : \"description\",    \"title\" : \"title\",    \"type\" : \"project\",    \"tags\" : [ \"tags\", \"tags\" ]  }, {    \"summary\" : \"summary\",    \"date\" : \"date\",    \"registeredMembers\" : [ \"registeredMembers\", \"registeredMembers\" ],    \"eventID\" : \"eventID\",    \"address\" : {      \"zip\" : 0,      \"city\" : \"city\",      \"street\" : \"street\"    },    \"currentMemberCount\" : 1,    \"possibleMemberCount\" : 6,    \"endDate\" : \"endDate\",    \"admin\" : {      \"avatar\" : \"avatar\",      \"userID\" : \"userID\",      \"username\" : \"username\",      \"token\" : \"token\"    },    \"description\" : \"description\",    \"title\" : \"title\",    \"type\" : \"project\",    \"tags\" : [ \"tags\", \"tags\" ]  } ]}", UserEventsResource.class), HttpStatus.NOT_IMPLEMENTED);
                } catch (IOException e) {
                    log.error("Couldn't serialize response for content type application/json", e);
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
        } else {
            log.warn("ObjectMapper or HttpServletRequest not configured in default EventApi interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }


    @ApiOperation(value = "Join an event", nickname = "joinEvent", notes = "Join an existing event", tags={ "event", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "Joined event"),
        @ApiResponse(code = 405, message = "Failed to join event") })
    @RequestMapping(value = "/event/join",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    default ResponseEntity<Void> joinEvent(@ApiParam(value = "Join Event Data"  )  @Valid @RequestBody JoinEventDataResource body) {
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
        } else {
            log.warn("ObjectMapper or HttpServletRequest not configured in default EventApi interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }


    @ApiOperation(value = "Leave an event", nickname = "leaveEvent", notes = "Leave an existing event", tags={ "event", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "Left event"),
        @ApiResponse(code = 405, message = "Failed to leave event") })
    @RequestMapping(value = "/event/leave",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    default ResponseEntity<Void> leaveEvent(@ApiParam(value = "Join Event Data"  )  @Valid @RequestBody LeaveEventDTOResource body) {
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
        } else {
            log.warn("ObjectMapper or HttpServletRequest not configured in default EventApi interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }


    @ApiOperation(value = "Report an event", nickname = "reportEvent", notes = "Report an event and send mail to admin team", tags={ "event", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "Joined event"),
        @ApiResponse(code = 405, message = "Failed to join event") })
    @RequestMapping(value = "/event/report",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    default ResponseEntity<Void> reportEvent(@ApiParam(value = "Report Event Data"  )  @Valid @RequestBody ReportDTOResource body) {
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
        } else {
            log.warn("ObjectMapper or HttpServletRequest not configured in default EventApi interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

}
