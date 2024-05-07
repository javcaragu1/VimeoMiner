package aiss.vimeominer.model.User;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {
    @JsonProperty("uri")
    private String uri;
    @JsonProperty("name")
    private String name;
    @JsonProperty("base_link")
    private String base_link;
    @JsonProperty("pictures")
    private Pictures pictures;

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

    @JsonProperty("base_link")
    public String getLink() {
        return base_link;
    }

    @JsonProperty("link")
    public void setLink(String base_link) {
        this.base_link = base_link;
    }

    @JsonProperty("pictures")
    public Pictures getPictures() {
        return pictures;
    }

    @JsonProperty("pictures")
    public void setPictures(Pictures pictures) {
        this.pictures = pictures;
    }
}
