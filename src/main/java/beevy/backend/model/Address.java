package beevy.backend.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.*;

/**
 * Address
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-03-21T17:25:50.041Z")

public class Address   {
  @JsonProperty("street")
  private String street = null;

  @JsonProperty("zip")
  private Integer zip = null;

  @JsonProperty("city")
  private String city = null;

  public Address street(String street) {
    this.street = street;
    return this;
  }

  /**
   * Get street
   * @return street
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public Address zip(Integer zip) {
    this.zip = zip;
    return this;
  }

  /**
   * Get zip
   * @return zip
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public Integer getZip() {
    return zip;
  }

  public void setZip(Integer zip) {
    this.zip = zip;
  }

  public Address city(String city) {
    this.city = city;
    return this;
  }

  /**
   * Get city
   * @return city
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Address address = (Address) o;
    return Objects.equals(this.street, address.street) &&
        Objects.equals(this.zip, address.zip) &&
        Objects.equals(this.city, address.city);
  }

  @Override
  public int hashCode() {
    return Objects.hash(street, zip, city);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Address {\n");
    
    sb.append("    street: ").append(toIndentedString(street)).append("\n");
    sb.append("    zip: ").append(toIndentedString(zip)).append("\n");
    sb.append("    city: ").append(toIndentedString(city)).append("\n");
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

