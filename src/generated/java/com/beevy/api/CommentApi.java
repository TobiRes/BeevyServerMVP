/**
 * NOTE: This class is auto generated by the swagger code generator program (2.3.1).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package com.beevy.api;

import com.beevy.model.CommentDTOResource;
import com.beevy.model.CommentResource;
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
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-05-31T16:31:35.567+02:00")

@Api(value = "comment", description = "the comment API")
public interface CommentApi {

    Logger log = LoggerFactory.getLogger(CommentApi.class);

    default Optional<ObjectMapper> getObjectMapper() {
        return Optional.empty();
    }

    default Optional<HttpServletRequest> getRequest() {
        return Optional.empty();
    }

    default Optional<String> getAcceptHeader() {
        return getRequest().map(r -> r.getHeader("Accept"));
    }

    @ApiOperation(value = "Add a comment", nickname = "addComment", notes = "Add a comment to an event or another comment", tags={ "comment", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "Added comment"),
        @ApiResponse(code = 405, message = "Failed to add comment") })
    @RequestMapping(value = "/comment",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    default ResponseEntity<Void> addComment(@ApiParam(value = "Comment Data"  )  @Valid @RequestBody CommentDTOResource body) {
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
        } else {
            log.warn("ObjectMapper or HttpServletRequest not configured in default CommentApi interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }


    @ApiOperation(value = "Get comments on an event", nickname = "getComments", notes = "Load all comments on an eventr", response = CommentResource.class, responseContainer = "List", tags={ "comment", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = CommentResource.class, responseContainer = "List"),
        @ApiResponse(code = 405, message = "Failed to get comments") })
    @RequestMapping(value = "/comment/{eventID}/{userID}/{tempAccessToken}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    default ResponseEntity<List<CommentResource>> getComments(@ApiParam(value = "ID of a User",required=true) @PathVariable("eventID") String eventID,@ApiParam(value = "ID of a User",required=true) @PathVariable("userID") String userID,@ApiParam(value = "tempAccessToken",required=true) @PathVariable("tempAccessToken") String tempAccessToken) {
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
            if (getAcceptHeader().get().contains("application/json")) {
                try {
                    return new ResponseEntity<>(getObjectMapper().get().readValue("[ {  \"createdAt\" : \"createdAt\",  \"comments\" : [ null, null ],  \"author\" : \"author\",  \"commentBody\" : \"commentBody\",  \"authorID\" : \"authorID\"}, {  \"createdAt\" : \"createdAt\",  \"comments\" : [ null, null ],  \"author\" : \"author\",  \"commentBody\" : \"commentBody\",  \"authorID\" : \"authorID\"} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
                } catch (IOException e) {
                    log.error("Couldn't serialize response for content type application/json", e);
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
        } else {
            log.warn("ObjectMapper or HttpServletRequest not configured in default CommentApi interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

}
