package aiss.vimeominer.service;

import aiss.vimeominer.model.Caption;
import aiss.vimeominer.model.CaptionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CaptionService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${vimeo.api.token}")
    private String vimeoApiToken;

    public List<Caption> getVideoCaption(String videoId) {
        String url = "https://api.vimeo.com/videos/" + videoId + "/texttracks";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "bearer " + vimeoApiToken);
        ResponseEntity<CaptionResponse> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                new HttpEntity<>(headers),
                CaptionResponse.class
        );

        CaptionResponse captionResponse = response.getBody();
        List<Caption> caption = captionResponse.getData();

        return caption;

    }


}
