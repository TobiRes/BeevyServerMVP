package com.beevy.model;

import java.util.Objects;
import com.beevy.model.EventResource;
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
 * UserEventsResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-06-20T15:45:15.112+02:00")

public class UserEventsResource   {
  @JsonProperty("joinedEvents")
  @Valid
  private List<EventResource> joinedEvents = null;

  @JsonProperty("createdEvents")
  @Valid
  private List<EventResource> createdEvents = null;

  public UserEventsResource joinedEvents(List<EventResource> joinedEvents) {
    this.joinedEvents = joinedEvents;
    return this;
  }

  public UserEventsResource addJoinedEventsItem(EventResource joinedEventsItem) {
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

  @Valid

  public List<EventResource> getJoinedEvents() {
    return joinedEvents;
  }

  public void setJoinedEvents(List<EventResource> joinedEvents) {
    this.joinedEvents = joinedEvents;
  }

  public UserEventsResource createdEvents(List<EventResource> createdEvents) {
    this.createdEvents = createdEvents;
    return this;
  }

  public UserEventsResource addCreatedEventsItem(EventResource createdEventsItem) {
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

  @Valid

  public List<EventResource> getCreatedEvents() {
    return createdEvents;
  }

  public void setCreatedEvents(List<EventResource> createdEvents) {
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
    UserEventsResource userEvents = (UserEventsResource) o;
    return Objects.equals(this.joinedEvents, userEvents.joinedEvents) &&
        Objects.equals(this.createdEvents, userEvents.createdEvents);
  }

  @Override
  public int hashCode() {
    return Objects.hash(joinedEvents, createdEvents);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UserEventsResource {\n");
    
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

