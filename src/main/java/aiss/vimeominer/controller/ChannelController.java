package aiss.vimeominer.controller;

import aiss.vimeominer.model.Channel.Channel;
import aiss.vimeominer.model.ChannelParser;
import aiss.vimeominer.model.Comment.Comment;
import aiss.vimeominer.model.Comment.CommentParser;
import aiss.vimeominer.model.Video;
import aiss.vimeominer.service.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/vimeo/channels")
public class ChannelController {

    @Value("${vimeo.api.token}")
    private String vimeoApiToken;
    private final RestTemplate restTemplate;

    // @Autowired
    // private VideoService videoService;

    @Autowired
    ChannelService channelService;


    @Autowired
    public ChannelController(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    /*@GetMapping("/{channel}")
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
    }*/


    //CREATE Y POST

    //POST http://localhost:8082/vimeo/channels/{key}/{id}
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{id}")
    public Channel create(@PathVariable String id,
                              @RequestParam(defaultValue = "10") Integer maxVideos,
                              @RequestHeader(defaultValue = "10") Integer maxComments) {

        Channel channel = channelService.getChannelDetails(id, "aa9d3c5e651d5df6c3545ae8ecb45329");
        // Filter videos
        List<Video> videos = channel.getVideos();
        if (maxVideos >= 0 && videos.size() > maxVideos) {
            channel.setVideos(videos.subList(0, maxVideos));
        }

        // Filter comments for each video
        for (Video video : channel.getVideos()) {
            List<CommentParser> comments = video.getComments();
            if (maxComments >= 0 && comments.size() > maxComments) {
                video.setComments(comments.subList(0, maxComments));
            }
        }

        String uri = "http://localhost:8080/VideoMiner/channels";
        HttpEntity<Channel> request = new HttpEntity<>(channel);
        ResponseEntity<Channel> response = restTemplate.exchange(
                uri,
                HttpMethod.POST,
                request,
                Channel.class
        );

        return response.getBody();
    }
    @GetMapping("/{id}")
    public ChannelParser getChannel(@PathVariable String id,
                                  @RequestParam(defaultValue = "10") Integer maxVideos,
                                  @RequestHeader(defaultValue = "10") Integer maxComments) {

        Channel channel = channelService.getChannelDetails(id, "aa9d3c5e651d5df6c3545ae8ecb45329");

        // Filter videos
        List<Video> videos = channel.getVideos();
        if (maxVideos >= 0 && videos.size() > maxVideos) {
            channel.setVideos(videos.subList(0, maxVideos));
        }

        // Filter comments for each video
        for (Video video : channel.getVideos()) {
            List<CommentParser> comments = video.getComments();
            if (maxComments >= 0 && comments.size() > maxComments) {
                video.setComments(comments.subList(0, maxComments));
            }
        }
        return ChannelParser.parseChannel(channel);
    }

}
