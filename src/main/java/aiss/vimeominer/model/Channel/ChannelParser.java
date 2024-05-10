package aiss.vimeominer.model;

import java.util.List;

import aiss.vimeominer.model.Channel.Channel;
import aiss.vimeominer.model.Video.Video;
import com.fasterxml.jackson.annotation.*;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChannelParser {


    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("description")
    private String description;
    @JsonProperty("created_time")
    private String created_time;
    @JsonProperty("videos")
    private List<Video> videos;

    @JsonProperty("id")
    public String getUri() {
        return id;
    }

    @JsonProperty("id")
    public void setUri(String id) {
        this.id = id;
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
        return "ChannelParser{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", created_time='" + created_time + '\'' +
                ", videos=" + videos +
                '}';
    }

    public ChannelParser(String id, String name, String description, String created_time, List<Video> videos) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.created_time = created_time;
        this.videos = videos;
    }

    public static ChannelParser parseChannel(Channel channel) {
        String id = channel.getUri().substring(channel.getUri().lastIndexOf("/") + 1);
        ChannelParser newChannel = new ChannelParser(id, channel.getName(), channel.getDescription(), channel.getCreatedTime(), channel.getVideos());
        return newChannel;
    }



}