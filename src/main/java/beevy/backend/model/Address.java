package beevy.backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public class Address {
    private String street = null;
    private Integer zip = null;
    private String city = null;

    public Address street(String street) {
        this.street = street;
        return this;
    }

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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }


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
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Address {\n");

        sb.append("    street: ").append(toIndentedString(street)).append("\n");
        sb.append("    zip: ").append(toIndentedString(zip)).append("\n");
        sb.append("    city: ").append(toIndentedString(city)).append("\n");
        sb.append("}");
        return sb.toString();
    }
}

