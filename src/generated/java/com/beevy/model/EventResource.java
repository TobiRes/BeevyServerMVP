package com.beevy.model;

import java.util.Objects;
import com.beevy.model.AddressResource;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * EventResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-03-26T15:37:57.761+02:00")

public class EventResource   {
  @JsonProperty("userID")
  private String userID = null;

  @JsonProperty("userSecret")
  private String userSecret = null;

  @JsonProperty("title")
  private String title = null;

  @JsonProperty("description")
  private String description = null;

  /**
   * Gets or Sets category
   */
  public enum CategoryEnum {
    CATEGORY1("category1"),
    
    CATEGORY2("category2"),
    
    CATEGORY3("category3");

    private String value;

    CategoryEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static CategoryEnum fromValue(String text) {
      for (CategoryEnum b : CategoryEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("category")
  private CategoryEnum category = null;

  @JsonProperty("memberCount")
  private Integer memberCount = null;

  @JsonProperty("time")
  private String time = null;

  @JsonProperty("location")
  private AddressResource location = null;

  public EventResource userID(String userID) {
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

  public EventResource userSecret(String userSecret) {
    this.userSecret = userSecret;
    return this;
  }

  /**
   * Get userSecret
   * @return userSecret
  **/
  @ApiModelProperty(value = "")


  public String getUserSecret() {
    return userSecret;
  }

  public void setUserSecret(String userSecret) {
    this.userSecret = userSecret;
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

  public EventResource category(CategoryEnum category) {
    this.category = category;
    return this;
  }

  /**
   * Get category
   * @return category
  **/
  @ApiModelProperty(value = "")


  public CategoryEnum getCategory() {
    return category;
  }

  public void setCategory(CategoryEnum category) {
    this.category = category;
  }

  public EventResource memberCount(Integer memberCount) {
    this.memberCount = memberCount;
    return this;
  }

  /**
   * Get memberCount
   * @return memberCount
  **/
  @ApiModelProperty(value = "")


  public Integer getMemberCount() {
    return memberCount;
  }

  public void setMemberCount(Integer memberCount) {
    this.memberCount = memberCount;
  }

  public EventResource time(String time) {
    this.time = time;
    return this;
  }

  /**
   * Get time
   * @return time
  **/
  @ApiModelProperty(value = "")


  public String getTime() {
    return time;
  }

  public void setTime(String time) {
    this.time = time;
  }

  public EventResource location(AddressResource location) {
    this.location = location;
    return this;
  }

  /**
   * Get location
   * @return location
  **/
  @ApiModelProperty(value = "")

  @Valid

  public AddressResource getLocation() {
    return location;
  }

  public void setLocation(AddressResource location) {
    this.location = location;
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
    return Objects.equals(this.userID, event.userID) &&
        Objects.equals(this.userSecret, event.userSecret) &&
        Objects.equals(this.title, event.title) &&
        Objects.equals(this.description, event.description) &&
        Objects.equals(this.category, event.category) &&
        Objects.equals(this.memberCount, event.memberCount) &&
        Objects.equals(this.time, event.time) &&
        Objects.equals(this.location, event.location);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userID, userSecret, title, description, category, memberCount, time, location);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class EventResource {\n");
    
    sb.append("    userID: ").append(toIndentedString(userID)).append("\n");
    sb.append("    userSecret: ").append(toIndentedString(userSecret)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    category: ").append(toIndentedString(category)).append("\n");
    sb.append("    memberCount: ").append(toIndentedString(memberCount)).append("\n");
    sb.append("    time: ").append(toIndentedString(time)).append("\n");
    sb.append("    location: ").append(toIndentedString(location)).append("\n");
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

