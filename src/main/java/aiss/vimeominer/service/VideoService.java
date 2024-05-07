package aiss.vimeominer.service;

import aiss.vimeominer.model.Comment.Comment;
import aiss.vimeominer.model.Video;
import aiss.vimeominer.model.VideoListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class VideoService {


    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CommentService commentService;

    @Autowired
    CaptionService captionService;

    @Value("${vimeo.api.token}")
    private String vimeoApiToken;


    public List<Video> getVideosChannel (String channelId) {
        String url = "https://api.vimeo.com/users/" + channelId + "/videos";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(headers);

        List<Video> allVideos = new ArrayList<>();

        Integer currentPage = 1;
        Boolean hasMorePages = true;

        while (hasMorePages) {
            ResponseEntity<VideoListResponse> responseEntity = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    VideoListResponse.class
            );

            VideoListResponse videoListResponse = responseEntity.getBody();
            List<Video> videos = videoListResponse.getData();

            for (Integer i = 0; i < videos.size(); i++) {
                String videoId = videos.get(i).getUri().substring(videos.get(i).getUri().lastIndexOf("/" + 1));
                List<Comment> comments = commentService.getVideoComments(videoId);
                videos.get(i).setComments(comments);
                videos.get(i).setCaption(captionService.getVideoCaption(videoId));
            }

            allVideos.addAll(videos);

            if (videoListResponse.getPaging().getNext() == null) {
                hasMorePages = false;
            } else {
                currentPage++;
            }


        }

        return allVideos;

    }

}
