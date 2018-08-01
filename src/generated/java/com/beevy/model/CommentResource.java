package com.beevy.model;

import java.util.Objects;
import com.beevy.model.CommentResource;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * CommentResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-08-01T10:20:41.953+02:00")

public class CommentResource   {
  @JsonProperty("commentID")
  private String commentID = null;

  @JsonProperty("author")
  private String author = null;

  @JsonProperty("authorID")
  private String authorID = null;

  @JsonProperty("createdAt")
  private String createdAt = null;

  @JsonProperty("commentBody")
  private String commentBody = null;

  @JsonProperty("comments")
  @Valid
  private List<CommentResource> comments = null;

  public CommentResource commentID(String commentID) {
    this.commentID = commentID;
    return this;
  }

  /**
   * Get commentID
   * @return commentID
  **/
  @ApiModelProperty(value = "")


  public String getCommentID() {
    return commentID;
  }

  public void setCommentID(String commentID) {
    this.commentID = commentID;
  }

  public CommentResource author(String author) {
    this.author = author;
    return this;
  }

  /**
   * Get author
   * @return author
  **/
  @ApiModelProperty(value = "")


  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public CommentResource authorID(String authorID) {
    this.authorID = authorID;
    return this;
  }

  /**
   * Get authorID
   * @return authorID
  **/
  @ApiModelProperty(value = "")


  public String getAuthorID() {
    return authorID;
  }

  public void setAuthorID(String authorID) {
    this.authorID = authorID;
  }

  public CommentResource createdAt(String createdAt) {
    this.createdAt = createdAt;
    return this;
  }

  /**
   * Get createdAt
   * @return createdAt
  **/
  @ApiModelProperty(value = "")


  public String getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }

  public CommentResource commentBody(String commentBody) {
    this.commentBody = commentBody;
    return this;
  }

  /**
   * Get commentBody
   * @return commentBody
  **/
  @ApiModelProperty(value = "")


  public String getCommentBody() {
    return commentBody;
  }

  public void setCommentBody(String commentBody) {
    this.commentBody = commentBody;
  }

  public CommentResource comments(List<CommentResource> comments) {
    this.comments = comments;
    return this;
  }

  public CommentResource addCommentsItem(CommentResource commentsItem) {
    if (this.comments == null) {
      this.comments = new ArrayList<>();
    }
    this.comments.add(commentsItem);
    return this;
  }

  /**
   * Get comments
   * @return comments
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<CommentResource> getComments() {
    return comments;
  }

  public void setComments(List<CommentResource> comments) {
    this.comments = comments;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CommentResource comment = (CommentResource) o;
    return Objects.equals(this.commentID, comment.commentID) &&
        Objects.equals(this.author, comment.author) &&
        Objects.equals(this.authorID, comment.authorID) &&
        Objects.equals(this.createdAt, comment.createdAt) &&
        Objects.equals(this.commentBody, comment.commentBody) &&
        Objects.equals(this.comments, comment.comments);
  }

  @Override
  public int hashCode() {
    return Objects.hash(commentID, author, authorID, createdAt, commentBody, comments);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CommentResource {\n");
    
    sb.append("    commentID: ").append(toIndentedString(commentID)).append("\n");
    sb.append("    author: ").append(toIndentedString(author)).append("\n");
    sb.append("    authorID: ").append(toIndentedString(authorID)).append("\n");
    sb.append("    createdAt: ").append(toIndentedString(createdAt)).append("\n");
    sb.append("    commentBody: ").append(toIndentedString(commentBody)).append("\n");
    sb.append("    comments: ").append(toIndentedString(comments)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

