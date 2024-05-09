package aiss.vimeominer.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
public class VideoListResponse {
    @JsonProperty("total")
    private int total;
    @JsonProperty("page")
    private int page;
    @JsonProperty("per_page")
    private int perPage;
    @JsonProperty("paging")
    private Paging paging;
    @JsonProperty("data")
    private List<Video> data;

    @JsonProperty("total")
    public int getTotal() {
        return total;
    }

    @JsonProperty("total")
    public void setTotal(int total) {
        this.total = total;
    }

    @JsonProperty("page")
    public int getPage() {
        return page;
    }

    @JsonProperty("page")
    public void setPage(int page) {
        this.page = page;
    }

    @JsonProperty("per_page")
    public int getPerPage() {
        return perPage;
    }

    @JsonProperty("per_page")
    public void setPerPage(int perPage) {
        this.perPage = perPage;
    }

    @JsonProperty("paging")
    public Paging getPaging() {
        return paging;
    }

    @JsonProperty("paging")
    public void setPaging(Paging paging) {
        this.paging = paging;
    }

    @JsonProperty("data")
    public List<Video> getData() {
        return data;
    }

    @JsonProperty("data")
    public void setData(List<Video> data) {
        this.data = data;
    }


    @Override
    public String toString() {
        return "VideoListResponse{" +
                "total=" + total +
                ", page=" + page +
                ", perPage=" + perPage +
                ", paging=" + paging +
                ", data=" + data +
                '}';
    }
}
