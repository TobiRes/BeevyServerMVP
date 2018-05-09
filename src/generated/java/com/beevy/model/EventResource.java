package com.beevy.model;

import java.util.Objects;
import com.beevy.model.AddressResource;
import com.beevy.model.UserResource;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * EventResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-05-09T14:28:34.909+02:00")

public class EventResource   {
  @JsonProperty("eventID")
  private String eventID = null;

  @JsonProperty("admin")
  private UserResource admin = null;

  @JsonProperty("title")
  private String title = null;

  @JsonProperty("summary")
  private String summary = null;

  @JsonProperty("description")
  private String description = null;

  /**
   * Gets or Sets type
   */
  public enum TypeEnum {
    EVENT("event"),
    
    PROJECT("project");

    private String value;

    TypeEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static TypeEnum fromValue(String text) {
      for (TypeEnum b : TypeEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("type")
  private TypeEnum type = null;

  @JsonProperty("date")
  private String date = null;

  @JsonProperty("endDate")
  private String endDate = null;

  @JsonProperty("address")
  private AddressResource address = null;

  @JsonProperty("registeredMembers")
  @Valid
  private List<String> registeredMembers = null;

  @JsonProperty("possibleMemberCount")
  private Integer possibleMemberCount = null;

  @JsonProperty("currentMemberCount")
  private Integer currentMemberCount = null;

  public EventResource eventID(String eventID) {
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

  public EventResource admin(UserResource admin) {
    this.admin = admin;
    return this;
  }

  /**
   * Get admin
   * @return admin
  **/
  @ApiModelProperty(value = "")

  @Valid

  public UserResource getAdmin() {
    return admin;
  }

  public void setAdmin(UserResource admin) {
    this.admin = admin;
  }

  public EventResource title(String title) {
    this.title = title;
    return this;
  }

  /**
   * Get title
   * @return title
  **/
  @ApiModelProperty(value = "")


  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public EventResource summary(String summary) {
    this.summary = summary;
    return this;
  }

  /**
   * Get summary
   * @return summary
  **/
  @ApiModelProperty(value = "")


  public String getSummary() {
    return summary;
  }

  public void setSummary(String summary) {
    this.summary = summary;
  }

  public EventResource description(String description) {
    this.description = description;
    return this;
  }

  /**
   * Get description
   * @return description
  **/
  @ApiModelProperty(value = "")


  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public EventResource type(TypeEnum type) {
    this.type = type;
    return this;
  }

  /**
   * Get type
   * @return type
  **/
  @ApiModelProperty(value = "")


  public TypeEnum getType() {
    return type;
  }

  public void setType(TypeEnum type) {
    this.type = type;
  }

  public EventResource date(String date) {
    this.date = date;
    return this;
  }

  /**
   * Get date
   * @return date
  **/
  @ApiModelProperty(value = "")


  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public EventResource endDate(String endDate) {
    this.endDate = endDate;
    return this;
  }

  /**
   * Get endDate
   * @return endDate
  **/
  @ApiModelProperty(value = "")


  public String getEndDate() {
    return endDate;
  }

  public void setEndDate(String endDate) {
    this.endDate = endDate;
  }

  public EventResource address(AddressResource address) {
    this.address = address;
    return this;
  }

  /**
   * Get address
   * @return address
  **/
  @ApiModelProperty(value = "")

  @Valid

  public AddressResource getAddress() {
    return address;
  }

  public void setAddress(AddressResource address) {
    this.address = address;
  }

  public EventResource registeredMembers(List<String> registeredMembers) {
    this.registeredMembers = registeredMembers;
    return this;
  }

  public EventResource addRegisteredMembersItem(String registeredMembersItem) {
    if (this.registeredMembers == null) {
      this.registeredMembers = new ArrayList<>();
    }
    this.registeredMembers.add(registeredMembersItem);
    return this;
  }

  /**
   * Get registeredMembers
   * @return registeredMembers
  **/
  @ApiModelProperty(value = "")


  public List<String> getRegisteredMembers() {
    return registeredMembers;
  }

  public void setRegisteredMembers(List<String> registeredMembers) {
    this.registeredMembers = registeredMembers;
  }

  public EventResource possibleMemberCount(Integer possibleMemberCount) {
    this.possibleMemberCount = possibleMemberCount;
    return this;
  }

  /**
   * Get possibleMemberCount
   * @return possibleMemberCount
  **/
  @ApiModelProperty(value = "")


  public Integer getPossibleMemberCount() {
    return possibleMemberCount;
  }

  public void setPossibleMemberCount(Integer possibleMemberCount) {
    this.possibleMemberCount = possibleMemberCount;
  }

  public EventResource currentMemberCount(Integer currentMemberCount) {
    this.currentMemberCount = currentMemberCount;
    return this;
  }

  /**
   * Get currentMemberCount
   * @return currentMemberCount
  **/
  @ApiModelProperty(value = "")


  public Integer getCurrentMemberCount() {
    return currentMemberCount;
  }

  public void setCurrentMemberCount(Integer currentMemberCount) {
    this.currentMemberCount = currentMemberCount;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EventResource event = (EventResource) o;
    return Objects.equals(this.eventID, event.eventID) &&
        Objects.equals(this.admin, event.admin) &&
        Objects.equals(this.title, event.title) &&
        Objects.equals(this.summary, event.summary) &&
        Objects.equals(this.description, event.description) &&
        Objects.equals(this.type, event.type) &&
        Objects.equals(this.date, event.date) &&
        Objects.equals(this.endDate, event.endDate) &&
        Objects.equals(this.address, event.address) &&
        Objects.equals(this.registeredMembers, event.registeredMembers) &&
        Objects.equals(this.possibleMemberCount, event.possibleMemberCount) &&
        Objects.equals(this.currentMemberCount, event.currentMemberCount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(eventID, admin, title, summary, description, type, date, endDate, address, registeredMembers, possibleMemberCount, currentMemberCount);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class EventResource {\n");
    
    sb.append("    eventID: ").append(toIndentedString(eventID)).append("\n");
    sb.append("    admin: ").append(toIndentedString(admin)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    summary: ").append(toIndentedString(summary)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    date: ").append(toIndentedString(date)).append("\n");
    sb.append("    endDate: ").append(toIndentedString(endDate)).append("\n");
    sb.append("    address: ").append(toIndentedString(address)).append("\n");
    sb.append("    registeredMembers: ").append(toIndentedString(registeredMembers)).append("\n");
    sb.append("    possibleMemberCount: ").append(toIndentedString(possibleMemberCount)).append("\n");
    sb.append("    currentMemberCount: ").append(toIndentedString(currentMemberCount)).append("\n");
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

