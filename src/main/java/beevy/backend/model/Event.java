package beevy.backend.model;

import com.beevy.model.AddressResource;
import com.beevy.model.EventResource;
import com.beevy.model.UserResource;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.List;

@Getter
@Setter
public class Event {

    @Id
    private String userID;
    private String userSecret;
    private UserResource admin;
    private String title;
    private String summary;
    private String description;
    private EventResource.TypeEnum type;
    private String date;
    private String endDate;
    private AddressResource address;
    private List<UserResource> registeredMembers;
    private Integer possibleMemberCount;
    private Integer currentMemberCount;

    public Event userID(String userID) {
        this.userID = userID;
        return this;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public Event userSecret(String userSecret) {
        this.userSecret = userSecret;
        return this;
    }

    public String getUserSecret() {
        return userSecret;
    }

    public void setUserSecret(String userSecret) {
        this.userSecret = userSecret;
    }

    public Event admin(UserResource admin) {
        this.admin = admin;
        return this;
    }

    public UserResource getAdmin() {
        return admin;
    }

    public void setAdmin(UserResource admin) {
        this.admin = admin;
    }

    public Event title(String title) {
        this.title = title;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Event summary(String summary) {
        this.summary = summary;
        return this;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Event description(String description) {
        this.description = description;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Event type(EventResource.TypeEnum category) {
        this.type = category;
        return this;
    }

    public EventResource.TypeEnum getType() {
        return type;
    }

    public void setType(EventResource.TypeEnum type) {
        this.type = type;
    }

    public Event date(String time) {
        this.date = time;
        return this;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Event endDate(String date) {
        this.endDate = date;
        return this;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String date) {
        this.endDate = date;
    }

    public Event address(AddressResource location) {
        this.address = location;
        return this;
    }

    public AddressResource getAddress() {
        return address;
    }

    public void setAddress(AddressResource address) {
        this.address = address;
    }

    public enum CategoryEnum {
        CATEGORY1("category1"),

        CATEGORY2("category2"),

        CATEGORY3("category3");

        private String value;

        CategoryEnum(String value) {
            this.value = value;
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

        @Override
        @JsonValue
        public String toString() {
            return String.valueOf(value);
        }
    }

    public Event registeredMembers(List<UserResource> users){
        this.registeredMembers = users;
        return this;
    }

    public List<UserResource> getRegisteredMembers(){
        return this.registeredMembers;
    }

    public void setRegisteredMembers(List<UserResource> users){
        this.registeredMembers = users;
    }

    public Event possibleMemberCount(Integer possibleMemberCount){
        this.possibleMemberCount = possibleMemberCount;
        return this;
    }

    public Integer getPossibleMemberCount(){
        return this.possibleMemberCount;
    }

    public void setPossibleMemberCount(Integer possibleMemberCount){
        this.possibleMemberCount = possibleMemberCount;
    }

    public Event currentMemberCount(Integer currentMemberCount){
        this.currentMemberCount = currentMemberCount;
        return this;
    }

    public Integer getCurrentMemberCount(){
        return this.currentMemberCount;
    }

    public void setCurrentMemberCount(Integer currentMemberCount) {
        this.currentMemberCount = currentMemberCount;
    }
}

