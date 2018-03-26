package beevy.backend.api;

import com.beevy.api.EventApi;
import com.beevy.model.EventResource;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
public class EventApiController implements EventApi {


    @Override
    public ResponseEntity<Void> createEvent(@ApiParam(value = "Event Object") @Valid @RequestBody EventResource body) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
