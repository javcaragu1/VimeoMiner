package aiss.vimeominer.service;


import aiss.vimeominer.model.Channel;
import aiss.vimeominer.model.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ChannelService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    VideoService videoService;

    //@Value("${vimeo.api.token}")
    //private String vimeoApiToken;

    public Channel getChannelDetails(String channelId , String token){
        String url = "https://api.vimeo.com/channels/" + channelId;
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<Channel> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                Channel.class
        );
        List<Video> videos = videoService.getVideosChannel(channelId);
        Channel channel = response.getBody();
        Channel channelEdited = new Channel(channel.getUri().substring(channel.getUri().lastIndexOf("/") + 1), channel.getName(), channel.getDescription(), channel.getCreatedTime(), videos);

        return channelEdited;

    }
}
