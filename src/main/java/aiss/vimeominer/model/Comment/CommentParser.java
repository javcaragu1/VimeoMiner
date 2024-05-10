package aiss.vimeominer.model.Comment;

import aiss.vimeominer.model.User.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommentParser {
    @JsonProperty("id")
    private String id;
    @JsonProperty("text")
    private String text;
    @JsonProperty("created_on")
    private String created_on;
    @JsonProperty("user")
    private User user;

    @JsonProperty("id")
    public String getUri() {
        return id;
    }

    @JsonProperty("id")
    public void setUri(String id) {
        this.id = id;
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
    public User getUser() {
        return user;
    }

    @JsonProperty("user")
    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "CommentParser{" +
                "id='" + id + '\'' +
                ", text='" + text + '\'' +
                ", created_on='" + created_on + '\'' +
                ", user=" + user +
                '}';
    }


    public CommentParser(String id, String text, String created_on, User user) {
        this.id = id;
        this.text = text;
        this.created_on = created_on;
        this.user = user;
    }



    public static CommentParser parseComment(Comment comment) {
        String id = comment.getUri().substring(comment.getUri().lastIndexOf("/") + 1);
        return new CommentParser(id, comment.getText(), comment.getCreatedOn(), comment.getUser());
    }


    public static List<CommentParser> parseComments(List<Comment> comments) {
        List<CommentParser> parsedComments = new ArrayList<>();
        for (Integer i = 0; i < comments.size(); i++){
            Comment comment = comments.get(i);
            parsedComments.add(parseComment(comment));
        }
        return parsedComments;
    }
}