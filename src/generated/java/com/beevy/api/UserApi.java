/**
 * NOTE: This class is auto generated by the swagger code generator program (2.3.1).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package com.beevy.api;

import com.beevy.model.UserResource;
import com.beevy.model.UserSecurityResource;
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

@Api(value = "user", description = "the user API")
public interface UserApi {

    Logger log = LoggerFactory.getLogger(UserApi.class);

    default Optional<ObjectMapper> getObjectMapper() {
        return Optional.empty();
    }

    default Optional<HttpServletRequest> getRequest() {
        return Optional.empty();
    }

    default Optional<String> getAcceptHeader() {
        return getRequest().map(r -> r.getHeader("Accept"));
    }

    @ApiOperation(value = "Return security token for a user", nickname = "getUserToken", notes = "Get security token", response = String.class, tags={ "user", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = String.class),
        @ApiResponse(code = 404, message = "User not found") })
    @RequestMapping(value = "/user/{username}/{userID}/{tempAccessToken}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    default ResponseEntity<String> getUserToken(@ApiParam(value = "username",required=true) @PathVariable("username") String username,@ApiParam(value = "ID of a User",required=true) @PathVariable("userID") String userID,@ApiParam(value = "tempAccessToken",required=true) @PathVariable("tempAccessToken") String tempAccessToken) {
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
            if (getAcceptHeader().get().contains("application/json")) {
                try {
                    return new ResponseEntity<>(getObjectMapper().get().readValue("\"\"", String.class), HttpStatus.NOT_IMPLEMENTED);
                } catch (IOException e) {
                    log.error("Couldn't serialize response for content type application/json", e);
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
        } else {
            log.warn("ObjectMapper or HttpServletRequest not configured in default UserApi interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }


    @ApiOperation(value = "Register user", nickname = "registerUser", notes = "Register a user via mail", tags={ "user", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "Sent registration mail"),
        @ApiResponse(code = 405, message = "Failed to send registration mail") })
    @RequestMapping(value = "/user/register",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    default ResponseEntity<Void> registerUser(@ApiParam(value = "Minimal User"  )  @Valid @RequestBody UserResource body) {
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
        } else {
            log.warn("ObjectMapper or HttpServletRequest not configured in default UserApi interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }


    @ApiOperation(value = "Set a temporary access token for a user", nickname = "setTempAccessTokenForUser", notes = "Set a temporary access token to make sure only the right user can get data", tags={ "user", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "Saved temporary access token"),
        @ApiResponse(code = 405, message = "Failed to create temporary access token") })
    @RequestMapping(value = "/user/access",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    default ResponseEntity<Void> setTempAccessTokenForUser(@ApiParam(value = "Security Object"  )  @Valid @RequestBody UserSecurityResource body) {
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
        } else {
            log.warn("ObjectMapper or HttpServletRequest not configured in default UserApi interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

}
