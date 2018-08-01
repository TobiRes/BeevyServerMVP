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
 * AdminEventDeleteResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-08-01T11:41:49.543+02:00")

public class AdminEventDeleteResource   {
  @JsonProperty("eventID")
  private String eventID = null;

  @JsonProperty("adminToken")
  private String adminToken = null;

  public AdminEventDeleteResource eventID(String eventID) {
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

  public AdminEventDeleteResource adminToken(String adminToken) {
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
    AdminEventDeleteResource adminEventDelete = (AdminEventDeleteResource) o;
    return Objects.equals(this.eventID, adminEventDelete.eventID) &&
        Objects.equals(this.adminToken, adminEventDelete.adminToken);
  }

  @Override
  public int hashCode() {
    return Objects.hash(eventID, adminToken);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AdminEventDeleteResource {\n");
    
    sb.append("    eventID: ").append(toIndentedString(eventID)).append("\n");
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

