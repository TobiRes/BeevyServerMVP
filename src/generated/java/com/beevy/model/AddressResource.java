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
 * AddressResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-05-24T12:13:31.782+02:00")

public class AddressResource   {
  @JsonProperty("street")
  private String street = null;

  @JsonProperty("zip")
  private Integer zip = null;

  @JsonProperty("city")
  private String city = null;

  @JsonProperty("joinedEvents")
  @Valid
  private List<String> joinedEvents = null;

  @JsonProperty("createdEvents")
  @Valid
  private List<String> createdEvents = null;

  public AddressResource street(String street) {
    this.street = street;
    return this;
  }

  /**
   * Get street
   * @return street
  **/
  @ApiModelProperty(value = "")


  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public AddressResource zip(Integer zip) {
    this.zip = zip;
    return this;
  }

  /**
   * Get zip
   * @return zip
  **/
  @ApiModelProperty(value = "")


  public Integer getZip() {
    return zip;
  }

  public void setZip(Integer zip) {
    this.zip = zip;
  }

  public AddressResource city(String city) {
    this.city = city;
    return this;
  }

  /**
   * Get city
   * @return city
  **/
  @ApiModelProperty(value = "")


  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public AddressResource joinedEvents(List<String> joinedEvents) {
    this.joinedEvents = joinedEvents;
    return this;
  }

  public AddressResource addJoinedEventsItem(String joinedEventsItem) {
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

  public AddressResource createdEvents(List<String> createdEvents) {
    this.createdEvents = createdEvents;
    return this;
  }

  public AddressResource addCreatedEventsItem(String createdEventsItem) {
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
    AddressResource address = (AddressResource) o;
    return Objects.equals(this.street, address.street) &&
        Objects.equals(this.zip, address.zip) &&
        Objects.equals(this.city, address.city) &&
        Objects.equals(this.joinedEvents, address.joinedEvents) &&
        Objects.equals(this.createdEvents, address.createdEvents);
  }

  @Override
  public int hashCode() {
    return Objects.hash(street, zip, city, joinedEvents, createdEvents);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AddressResource {\n");
    
    sb.append("    street: ").append(toIndentedString(street)).append("\n");
    sb.append("    zip: ").append(toIndentedString(zip)).append("\n");
    sb.append("    city: ").append(toIndentedString(city)).append("\n");
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

