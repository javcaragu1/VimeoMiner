package aiss.vimeominer.model;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import aiss.vimeominer.model.User.User;
import com.fasterxml.jackson.annotation.*;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Channel {
    @JsonProperty("uri")
    private String uri;
    @JsonProperty("name")
    private String name;
    @JsonProperty("description")
    private String description;
    @JsonProperty("created_time")
    private String created_time;
    @JsonProperty("user")
    private User user;
    @JsonProperty("videos")
    private List<Video> videos;

    @JsonProperty("uri")
    public String getUri() {
        return uri;
    }

    @JsonProperty("uri")
    public void setUri(String uri) {
        this.uri = uri;
    }

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

    @JsonProperty("created_time")
    public String getCreatedTime() {
        return created_time;
    }

    @JsonProperty("created_time")
    public void setCreatedTime(String created_time) {
        this.created_time = created_time;
    }

    @JsonProperty("user")
    public User getUser() {
        return user;
    }

    @JsonProperty("user")
    public void setUser(User user) {
        this.user = user;
    }

    @JsonProperty("videos")
    public List<Video> getVideos() {
        return videos;
    }
    @JsonProperty("videos")
    public void setVideos(List<Video> videos) {
        this.videos = videos;
    }


    @Override
    public String toString() {
        return "Channel{" +
                "uri='" + uri + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", created_time='" + created_time + '\'' +
                ", user=" + user +
                ", videos=" + videos +
                '}';
    }
}

