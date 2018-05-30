package beevy.backend.repositories;

import beevy.backend.model.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends MongoRepository<Comment, String> {

    public Comment findByCommentID(String commentID);
}
