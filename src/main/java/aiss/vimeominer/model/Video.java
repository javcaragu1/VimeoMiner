package aiss.vimeominer.model;

import aiss.vimeominer.model.Comment.Comment;
import com.fasterxml.jackson.annotation.*;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Video {
    @JsonProperty("uri")
    private String uri;
    @JsonProperty("name")
    private String name;
    @JsonProperty("description")
    private String description;
    @JsonProperty("release_time")
    private String release_time;
    private List<Comment> comments;

    private List<Caption> caption;

    @JsonProperty("uri")
    public String getUri() { return uri; }

    @JsonProperty("uri")
    public void setUri(String uri) { this.uri = uri; }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("release_time")
    public String getReleaseTime() { return release_time; }

    @JsonProperty("release_time")
    public void setReleaseTime(String release_time) { this.release_time = release_time; }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Caption> getCaption() { return caption; }

    public void setCaption(List<Caption> caption) { this.caption = caption; }
}
