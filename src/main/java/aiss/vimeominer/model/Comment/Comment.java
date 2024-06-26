package aiss.vimeominer.model.Comment;

import aiss.vimeominer.model.User.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;



@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Comment {
    @JsonProperty("uri")
    private String uri;
    @JsonProperty("text")
    private String text;
    @JsonProperty("created_on")
    private String created_on;
    @JsonProperty("user")
    private User user;

    @JsonProperty("uri")
    public String getUri() {
        return uri;
    }

    @JsonProperty("uri")
    public void setUri(String uri) {
        this.uri = uri;
    }

    @JsonProperty("text")
    public String getText() {
        return text;
    }

    @JsonProperty("text")
    public void setText(String text) {
        this.text = text;
    }

    @JsonProperty("created_on")
    public String getCreatedOn() {
        return created_on;
    }

    @JsonProperty("created_on")
    public void setCreatedOn(String created_on) {
        this.created_on = created_on;
    }

    @JsonProperty("user")
    public User getUser() { return user; }

    @JsonProperty("user")
    public void setUser(User user) { this.user = user; }

    public Comment(String id, String text, String created_on, User user) {
        this.uri = id;
        this.text = text;
        this.created_on = created_on;
        User newUser = new User(user.getId(), user.getName(), user.getLink(), user.getPictures());
        this.user = newUser;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id='" + uri + '\'' +
                ", text='" + text + '\'' +
                ", created_on='" + created_on + '\'' +
                ", user=" + user +
                '}';
    }
}
