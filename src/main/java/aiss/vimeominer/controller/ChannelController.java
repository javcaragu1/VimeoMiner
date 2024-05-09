package aiss.vimeominer.controller;

import aiss.vimeominer.model.Channel;
import aiss.vimeominer.model.Comment.Comment;
import aiss.vimeominer.model.User.User;
import aiss.vimeominer.service.VideoService;
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

import java.util.List;

@RestController
@RequestMapping("/vimeo/channels")
public class ChannelController {

    @Value("${vimeo.api.token}")
    private String vimeoApiToken;
    private final RestTemplate restTemplate;

    @Autowired
    private VideoService videoService;
    @Autowired
    public ChannelController(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    @GetMapping("/{channel}")
    public ResponseEntity<Channel> getChannel(@PathVariable String channel) {
        String url = "https://api.vimeo.com/channels/" + channel;
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + vimeoApiToken);

        ResponseEntity<Channel> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                new HttpEntity<>(headers),
                Channel.class
        );
        String userId = response.getBody().getUser().getUri();
        userId = userId.substring(userId.lastIndexOf("/") + 1);

        response.getBody().setVideos(videoService.getVideosChannel(userId));

        return ResponseEntity.ok(response.getBody());
    }
}
