package aiss.vimeominer.model;

import aiss.vimeominer.model.Comment.Comment;
import aiss.vimeominer.model.Comment.CommentParser;
import com.fasterxml.jackson.annotation.*;

import java.util.ArrayList;
import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
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
    private List<CommentParser> comments;

    private List<Caption> caption;

    public Video() {
        comments = new ArrayList<>();
        caption = new ArrayList<>();
    }

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

    public List<CommentParser> getComments() {
        return comments;
    }

    public void setComments(List<CommentParser> comments) {
        this.comments = comments;
    }

    public List<Caption> getCaption() { return caption; }

    public void setCaption(List<Caption> caption) { this.caption = caption; }


    public Video(String id, String name, String description, String release_time, List<CommentParser> comments, List<Caption> caption) {
        this.uri = id;
        this.name = name;
        this.description = description;
        this.release_time = release_time;
        this.comments = comments;
        this.caption = caption;
    }

    @Override
    public String toString() {
        return "Video{" +
                "id='" + uri + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", release_time='" + release_time + '\'' +
                ", comments=" + comments +
                ", caption=" + caption +
                '}';
    }
}
