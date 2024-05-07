package aiss.vimeominer.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Caption {
    @JsonProperty("display_language")
    private String displayLanguage;
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("name")
    private String name;

    @JsonProperty("display_language")
    public String getDisplayLanguage() {
        return displayLanguage;
    }

    @JsonProperty("display_language")
    public void setDisplayLanguage(String displayLanguage) {
        this.displayLanguage = displayLanguage;
    }

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("name")
    public String getName() { return name; }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

}
