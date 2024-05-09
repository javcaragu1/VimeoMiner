package aiss.vimeominer.service;

import aiss.vimeominer.model.Comment.Comment;
import aiss.vimeominer.model.Comment.CommentListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.Collections;
import java.util.List;


@Service
public class CommentService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${vimeo.api.token}")
    private String vimeoApiToken;
    public List<Comment> getVideoComments(String videoId) {
        String url = "https://api.vimeo.com/videos/" + videoId + "/comments";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + vimeoApiToken);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<CommentListResponse> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                CommentListResponse.class
        );
        CommentListResponse commentListResponse = responseEntity.getBody();
        if (commentListResponse != null) {
            return commentListResponse.getData();
        }else {
            return Collections.emptyList();
        }
    }


}
