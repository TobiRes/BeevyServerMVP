package beevy.backend.api;

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

        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }
}
