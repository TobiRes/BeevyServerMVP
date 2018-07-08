package beevy.backend.converter;

import beevy.backend.model.Comment;
import com.beevy.model.CommentResource;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class CommentEntityToResourceConverter {

    public CommentResource toResource(Comment comment) {
        return new CommentResource()
                .commentID(comment.getCommentID())
                .author(comment.getCommentAuthor())
                .authorID(comment.getAuthorID())
                .commentBody(comment.getCommentBody())
                .createdAt(comment.getCommentTime())
                .comments(addComments(comment));
    }

    private List<CommentResource> addComments(Comment comment) {
        if (comment.getComments() == null) {
            return null;
        } else {
            List<Comment> comments = comment.getComments();
            List<CommentResource> commentResources = new ArrayList<>();
            comments.forEach(commentEntity -> {
                CommentResource commentResource = toResource(commentEntity);
                commentResources.add(commentResource);
            });
            return commentResources;
        }
    }
}
