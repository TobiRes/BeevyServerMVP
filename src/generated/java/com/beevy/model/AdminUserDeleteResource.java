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
 * AdminUserDeleteResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-08-01T11:14:59.938+02:00")

public class AdminUserDeleteResource   {
  @JsonProperty("userID")
  private String userID = null;

  @JsonProperty("adminToken")
  private String adminToken = null;

  public AdminUserDeleteResource userID(String userID) {
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

  public AdminUserDeleteResource adminToken(String adminToken) {
    this.adminToken = adminToken;
    return this;
  }

  /**
   * Get adminToken
   * @return adminToken
  **/
  @ApiModelProperty(value = "")


  public String getAdminToken() {
    return adminToken;
  }

  public void setAdminToken(String adminToken) {
    this.adminToken = adminToken;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AdminUserDeleteResource adminUserDelete = (AdminUserDeleteResource) o;
    return Objects.equals(this.userID, adminUserDelete.userID) &&
        Objects.equals(this.adminToken, adminUserDelete.adminToken);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userID, adminToken);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AdminUserDeleteResource {\n");
    
    sb.append("    userID: ").append(toIndentedString(userID)).append("\n");
    sb.append("    adminToken: ").append(toIndentedString(adminToken)).append("\n");
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

