package com.beevy.model;

import java.util.Objects;
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
 * UserResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-06-22T15:44:13.065+02:00")

public class UserResource   {
  @JsonProperty("username")
  private String username = null;

  @JsonProperty("userID")
  private String userID = null;

  @JsonProperty("mail")
  private String mail = null;

  @JsonProperty("token")
  private String token = null;

  @JsonProperty("tempAccessToken")
  private String tempAccessToken = null;

  @JsonProperty("joinedEvents")
  @Valid
  private List<String> joinedEvents = null;

  @JsonProperty("createdEvents")
  @Valid
  private List<String> createdEvents = null;

  public UserResource username(String username) {
    this.username = username;
    return this;
  }

  /**
   * Get username
   * @return username
  **/
  @ApiModelProperty(value = "")


  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public UserResource userID(String userID) {
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

  public UserResource mail(String mail) {
    this.mail = mail;
    return this;
  }

  /**
   * Get mail
   * @return mail
  **/
  @ApiModelProperty(value = "")


  public String getMail() {
    return mail;
  }

  public void setMail(String mail) {
    this.mail = mail;
  }

  public UserResource token(String token) {
    this.token = token;
    return this;
  }

  /**
   * Get token
   * @return token
  **/
  @ApiModelProperty(value = "")


  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public UserResource tempAccessToken(String tempAccessToken) {
    this.tempAccessToken = tempAccessToken;
    return this;
  }

  /**
   * Get tempAccessToken
   * @return tempAccessToken
  **/
  @ApiModelProperty(value = "")


  public String getTempAccessToken() {
    return tempAccessToken;
  }

  public void setTempAccessToken(String tempAccessToken) {
    this.tempAccessToken = tempAccessToken;
  }

  public UserResource joinedEvents(List<String> joinedEvents) {
    this.joinedEvents = joinedEvents;
    return this;
  }

  public UserResource addJoinedEventsItem(String joinedEventsItem) {
    if (this.joinedEvents == null) {
      this.joinedEvents = new ArrayList<>();
    }
    this.joinedEvents.add(joinedEventsItem);
    return this;
  }

  /**
   * Get joinedEvents
   * @return joinedEvents
  **/
  @ApiModelProperty(value = "")


  public List<String> getJoinedEvents() {
    return joinedEvents;
  }

  public void setJoinedEvents(List<String> joinedEvents) {
    this.joinedEvents = joinedEvents;
  }

  public UserResource createdEvents(List<String> createdEvents) {
    this.createdEvents = createdEvents;
    return this;
  }

  public UserResource addCreatedEventsItem(String createdEventsItem) {
    if (this.createdEvents == null) {
      this.createdEvents = new ArrayList<>();
    }
    this.createdEvents.add(createdEventsItem);
    return this;
  }

  /**
   * Get createdEvents
   * @return createdEvents
  **/
  @ApiModelProperty(value = "")


  public List<String> getCreatedEvents() {
    return createdEvents;
  }

  public void setCreatedEvents(List<String> createdEvents) {
    this.createdEvents = createdEvents;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserResource user = (UserResource) o;
    return Objects.equals(this.username, user.username) &&
        Objects.equals(this.userID, user.userID) &&
        Objects.equals(this.mail, user.mail) &&
        Objects.equals(this.token, user.token) &&
        Objects.equals(this.tempAccessToken, user.tempAccessToken) &&
        Objects.equals(this.joinedEvents, user.joinedEvents) &&
        Objects.equals(this.createdEvents, user.createdEvents);
  }

  @Override
  public int hashCode() {
    return Objects.hash(username, userID, mail, token, tempAccessToken, joinedEvents, createdEvents);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UserResource {\n");
    
    sb.append("    username: ").append(toIndentedString(username)).append("\n");
    sb.append("    userID: ").append(toIndentedString(userID)).append("\n");
    sb.append("    mail: ").append(toIndentedString(mail)).append("\n");
    sb.append("    token: ").append(toIndentedString(token)).append("\n");
    sb.append("    tempAccessToken: ").append(toIndentedString(tempAccessToken)).append("\n");
    sb.append("    joinedEvents: ").append(toIndentedString(joinedEvents)).append("\n");
    sb.append("    createdEvents: ").append(toIndentedString(createdEvents)).append("\n");
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

