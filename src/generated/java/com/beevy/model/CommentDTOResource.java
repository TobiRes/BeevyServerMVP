package com.beevy.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * CommentDTOResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-05-30T16:18:21.859+02:00")

public class CommentDTOResource   {
  @JsonProperty("userID")
  private String userID = null;

  @JsonProperty("userToken")
  private String userToken = null;

  @JsonProperty("eventID")
  private String eventID = null;

  @JsonProperty("repliedTo")
  private String repliedTo = null;

  @JsonProperty("commentBody")
  private String commentBody = null;

  @JsonProperty("commentTime")
  private String commentTime = null;

  public CommentDTOResource userID(String userID) {
    this.userID = userID;
    return this;
  }

  /**
   * Get userID
   * @return userID
  **/
  @ApiModelProperty(value = "")


  public String getUserID() {
    return userID;
  }

  public void setUserID(String userID) {
    this.userID = userID;
  }

  public CommentDTOResource userToken(String userToken) {
    this.userToken = userToken;
    return this;
  }

  /**
   * Get userToken
   * @return userToken
  **/
  @ApiModelProperty(value = "")


  public String getUserToken() {
    return userToken;
  }

  public void setUserToken(String userToken) {
    this.userToken = userToken;
  }

  public CommentDTOResource eventID(String eventID) {
    this.eventID = eventID;
    return this;
  }

  /**
   * Get eventID
   * @return eventID
  **/
  @ApiModelProperty(value = "")


  public String getEventID() {
    return eventID;
  }

  public void setEventID(String eventID) {
    this.eventID = eventID;
  }

  public CommentDTOResource repliedTo(String repliedTo) {
    this.repliedTo = repliedTo;
    return this;
  }

  /**
   * Get repliedTo
   * @return repliedTo
  **/
  @ApiModelProperty(value = "")


  public String getRepliedTo() {
    return repliedTo;
  }

  public void setRepliedTo(String repliedTo) {
    this.repliedTo = repliedTo;
  }

  public CommentDTOResource commentBody(String commentBody) {
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

  public CommentDTOResource commentTime(String commentTime) {
    this.commentTime = commentTime;
    return this;
  }

  /**
   * Get commentTime
   * @return commentTime
  **/
  @ApiModelProperty(value = "")


  public String getCommentTime() {
    return commentTime;
  }

  public void setCommentTime(String commentTime) {
    this.commentTime = commentTime;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CommentDTOResource commentDTO = (CommentDTOResource) o;
    return Objects.equals(this.userID, commentDTO.userID) &&
        Objects.equals(this.userToken, commentDTO.userToken) &&
        Objects.equals(this.eventID, commentDTO.eventID) &&
        Objects.equals(this.repliedTo, commentDTO.repliedTo) &&
        Objects.equals(this.commentBody, commentDTO.commentBody) &&
        Objects.equals(this.commentTime, commentDTO.commentTime);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userID, userToken, eventID, repliedTo, commentBody, commentTime);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CommentDTOResource {\n");
    
    sb.append("    userID: ").append(toIndentedString(userID)).append("\n");
    sb.append("    userToken: ").append(toIndentedString(userToken)).append("\n");
    sb.append("    eventID: ").append(toIndentedString(eventID)).append("\n");
    sb.append("    repliedTo: ").append(toIndentedString(repliedTo)).append("\n");
    sb.append("    commentBody: ").append(toIndentedString(commentBody)).append("\n");
    sb.append("    commentTime: ").append(toIndentedString(commentTime)).append("\n");
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

