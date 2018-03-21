package io.swagger.api;

import io.swagger.model.Event;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-03-21T17:25:50.041Z")

@Controller
public class EventApiController implements EventApi {

    private static final Logger log = LoggerFactory.getLogger(EventApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public EventApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Void> createEvent(@ApiParam(value = "Event Object" ,required=true )  @Valid @RequestBody Event body) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<List<Event>> getEvents() {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<List<Event>>(objectMapper.readValue("[ {  \"userSecret\" : \"userSecret\",  \"memberCount\" : 0,  \"description\" : \"description\",  \"location\" : {    \"zip\" : 6,    \"city\" : \"city\",    \"street\" : \"street\"  },  \"time\" : \"2000-01-23T04:56:07.000+00:00\",  \"title\" : \"title\",  \"category\" : \"category1\",  \"userID\" : \"userID\"}, {  \"userSecret\" : \"userSecret\",  \"memberCount\" : 0,  \"description\" : \"description\",  \"location\" : {    \"zip\" : 6,    \"city\" : \"city\",    \"street\" : \"street\"  },  \"time\" : \"2000-01-23T04:56:07.000+00:00\",  \"title\" : \"title\",  \"category\" : \"category1\",  \"userID\" : \"userID\"} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<Event>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<Event>>(HttpStatus.NOT_IMPLEMENTED);
    }

}
