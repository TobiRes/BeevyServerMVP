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
 * UserResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-05-05T14:03:06.247+02:00")

public class UserResource   {
  @JsonProperty("username")
  private String username = null;

  @JsonProperty("userID")
  private String userID = null;

  @JsonProperty("mail")
  private String mail = null;

  @JsonProperty("token")
  private String token = null;

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
        Objects.equals(this.token, user.token);
  }

  @Override
  public int hashCode() {
    return Objects.hash(username, userID, mail, token);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UserResource {\n");
    
    sb.append("    username: ").append(toIndentedString(username)).append("\n");
    sb.append("    userID: ").append(toIndentedString(userID)).append("\n");
    sb.append("    mail: ").append(toIndentedString(mail)).append("\n");
    sb.append("    token: ").append(toIndentedString(token)).append("\n");
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

