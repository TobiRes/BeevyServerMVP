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
 * AdminCommentDeleteResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-08-01T10:20:41.953+02:00")

public class AdminCommentDeleteResource   {
  @JsonProperty("commentID")
  private String commentID = null;

  @JsonProperty("adminToken")
  private String adminToken = null;

  public AdminCommentDeleteResource commentID(String commentID) {
    this.commentID = commentID;
    return this;
  }

  /**
   * Get commentID
   * @return commentID
  **/
  @ApiModelProperty(value = "")


  public String getCommentID() {
    return commentID;
  }

  public void setCommentID(String commentID) {
    this.commentID = commentID;
  }

  public AdminCommentDeleteResource adminToken(String adminToken) {
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
    AdminCommentDeleteResource adminCommentDelete = (AdminCommentDeleteResource) o;
    return Objects.equals(this.commentID, adminCommentDelete.commentID) &&
        Objects.equals(this.adminToken, adminCommentDelete.adminToken);
  }

  @Override
  public int hashCode() {
    return Objects.hash(commentID, adminToken);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AdminCommentDeleteResource {\n");
    
    sb.append("    commentID: ").append(toIndentedString(commentID)).append("\n");
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

