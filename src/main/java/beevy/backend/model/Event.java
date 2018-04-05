package beevy.backend.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.data.annotation.Id;




public class Event {

    @Id
    private String userID = null;
    private String userSecret = null;
    private String title = null;
    private String description = null;
    private CategoryEnum category = null;
    private Integer memberCount = null;
    private String time = null;
    private Address location = null;

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

    public Event category(CategoryEnum category) {
        this.category = category;
        return this;
    }



    public CategoryEnum getCategory() {
        return category;
    }

    public void setCategory(CategoryEnum category) {
        this.category = category;
    }

    public Event memberCount(Integer memberCount) {
        this.memberCount = memberCount;
        return this;
    }



    public Integer getMemberCount() {
        return memberCount;
    }

    public void setMemberCount(Integer memberCount) {
        this.memberCount = memberCount;
    }

    public Event time(String time) {
        this.time = time;
        return this;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Event location(Address location) {
        this.location = location;
        return this;
    }


    public Address getLocation() {
        return location;
    }

    public void setLocation(Address location) {
        this.location = location;
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
}

