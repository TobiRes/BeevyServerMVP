package beevy.backend.repositories;

import beevy.backend.model.Event;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends MongoRepository<Event, String> {

    public List<Event> findAllByUserSecretIsTrue();
}