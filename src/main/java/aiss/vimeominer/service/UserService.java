package aiss.vimeominer.service;

import aiss.vimeominer.model.Comment.Comment;
import aiss.vimeominer.model.Comment.CommentListResponse;
import aiss.vimeominer.model.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import aiss.vimeominer.service.CommentService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private CommentService commentService;
    @Autowired
    RestTemplate restTemplate;

    @Value("${vimeo.api.token}")
    private String vimeoApiToken;



    public User getUserDetails(String userId) {
        String url = "https://api.vimeo.com/users/" + userId;
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + vimeoApiToken);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<User> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                User.class
        );
        User user = responseEntity.getBody();
        User userEdited = new User(user.getUri().substring(user.getUri().lastIndexOf("/") + 1), user.getName(), user.getLink(), user.getPictures());
        if (user != null) {
            return userEdited;
        } else {
            return null;
        }

    }

    public User getUserComment(String commentId, String videoId) {
        //String videoId = String.valueOf(97);
        List<Comment> response = commentService.getVideoComments(videoId);
        User user = null;
        if (response != null) {
            for (Integer i = 0; i < response.size(); i++) {
                Comment comment = response.get(i);
                String id = comment.getUri().substring(comment.getUri().lastIndexOf("/") + 1);
                if (id.equals(commentId)) {
                    user = comment.getUser();
                    user = new User(user.getUri().substring(user.getUri().lastIndexOf("/") + 1), user.getName(), user.getLink(), user.getPictures());
                }

            }
        }

        return user;
    }



}
