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
 * UserSecurityResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-08-01T10:20:41.953+02:00")

public class UserSecurityResource   {
  @JsonProperty("username")
  private String username = null;

  @JsonProperty("userID")
  private String userID = null;

  @JsonProperty("token")
  private String token = null;

  @JsonProperty("tempToken")
  private String tempToken = null;

  public UserSecurityResource username(String username) {
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

  public UserSecurityResource userID(String userID) {
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

  public UserSecurityResource token(String token) {
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

  public UserSecurityResource tempToken(String tempToken) {
    this.tempToken = tempToken;
    return this;
  }

  /**
   * Get tempToken
   * @return tempToken
  **/
  @ApiModelProperty(value = "")


  public String getTempToken() {
    return tempToken;
  }

  public void setTempToken(String tempToken) {
    this.tempToken = tempToken;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserSecurityResource userSecurity = (UserSecurityResource) o;
    return Objects.equals(this.username, userSecurity.username) &&
        Objects.equals(this.userID, userSecurity.userID) &&
        Objects.equals(this.token, userSecurity.token) &&
        Objects.equals(this.tempToken, userSecurity.tempToken);
  }

  @Override
  public int hashCode() {
    return Objects.hash(username, userID, token, tempToken);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UserSecurityResource {\n");
    
    sb.append("    username: ").append(toIndentedString(username)).append("\n");
    sb.append("    userID: ").append(toIndentedString(userID)).append("\n");
    sb.append("    token: ").append(toIndentedString(token)).append("\n");
    sb.append("    tempToken: ").append(toIndentedString(tempToken)).append("\n");
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

