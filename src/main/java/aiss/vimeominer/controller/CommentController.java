package aiss.vimeominer.controller;

import aiss.vimeominer.model.Comment.Comment;
import aiss.vimeominer.model.Comment.CommentListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

//420853048

@RestController
@RequestMapping("/vimeo/videos")
public class CommentController {

    @Value("${vimeo.api.token}")
    private String vimeoApiToken;
    private final RestTemplate restTemplate;

    @Autowired
    public CommentController(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    @GetMapping("/{id}/comments")
    public ResponseEntity<List<Comment>> getVideoComments(@PathVariable String id) {
        String url = "https://api.vimeo.com/videos/" + id + "/comments";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + vimeoApiToken);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        List<Comment> allComments = new ArrayList<>();
        int currentPage = 1;
        boolean hasMorePages = true;

        while (hasMorePages) {
            ResponseEntity<CommentListResponse> responseEntity = restTemplate.exchange(
                    url + "?page=" + currentPage,
                    HttpMethod.GET,
                    entity,
                    CommentListResponse.class
            );

            CommentListResponse commentListResponse = responseEntity.getBody();
            List<Comment> comments = commentListResponse.getData();
            allComments.addAll(comments);

            if (commentListResponse.getPaging().getNext() == null) {
                hasMorePages = false;
            } else {
                currentPage++;
            }
        }

        return ResponseEntity.ok(allComments);
    }
}
