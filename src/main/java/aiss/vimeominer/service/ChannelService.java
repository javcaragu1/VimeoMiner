package aiss.vimeominer.service;


import aiss.vimeominer.model.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ChannelService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    VideoService videoService;

    @Value("${vimeo.api.token}")
    private String vimeoApiToken;

    public Channel getChannelDetails(String channelId){
        String url = "https://api.vimeo.com/channels/" + channelId;
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + vimeoApiToken);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<Channel> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                Channel.class
        );

        return response.getBody();

    }
}