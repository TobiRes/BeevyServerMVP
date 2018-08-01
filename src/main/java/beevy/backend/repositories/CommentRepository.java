package beevy.backend.repositories;

import beevy.backend.model.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends MongoRepository<Comment, String> {

    public Comment findByCommentID(String commentID);

    public List<Comment> deleteAllByEventID(String eventID);
}
