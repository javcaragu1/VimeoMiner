package aiss.vimeominer.controller;


import aiss.vimeominer.model.Channel;
import aiss.vimeominer.model.Comment.Comment;
import aiss.vimeominer.model.Video;
import aiss.vimeominer.model.VideoListResponse;
import aiss.vimeominer.service.VideoService;
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

    @Autowired
    private VideoService videoService;
    @Autowired
    public ChannelController(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }



    @GetMapping("/videos/{channel_id}")
    public ResponseEntity<List<Video>> getVideos(@PathVariable String channel_id) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + vimeoApiToken);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        String url = "https://api.vimeo.com/users/" + channel_id + "/videos";

        List<Video> allVideos = new ArrayList<>();
        int currentPage = 1;
        boolean hasMorePages = true;

        while (hasMorePages) {
            ResponseEntity<VideoListResponse> responseEntity = restTemplate.exchange(
                    url + "?page=" + currentPage,
                    HttpMethod.GET,
                    entity,
                    VideoListResponse.class
            );

            VideoListResponse videoListResponse = responseEntity.getBody();
            List<Video> videos = videoListResponse.getData();

            for (Video video : videos) {
                String id = video.getUri().substring(video.getUri().lastIndexOf("/") + 1);
                List<Comment> comments = commentService.getVideoComments(id);
                video.setComments(comments);
                video.setCaption(captionService.getVideoCaption(id));
            }

            allVideos.addAll(videos);

            if (videoListResponse.getPaging().getNext() == null) {
                hasMorePages = false;
            } else {
                currentPage++;
            }
        }

        return ResponseEntity.ok(allVideos);
    }

    @ExceptionHandler(VideoNotFoundException.class)
    public ResponseEntity<String> handleVideoNotFoundException(VideoNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error 404 - : " + ex.getMessage());
    }
}