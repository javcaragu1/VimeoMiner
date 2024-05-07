package aiss.vimeominer.controller;


import aiss.vimeominer.model.Caption;
import aiss.vimeominer.model.CaptionResponse;
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

@RestController
@RequestMapping("vimeo/caption")
public class CaptionController {
    @Value("${vimeo.api.token}")
    private String vimeoApiToken;

    private final RestTemplate restTemplate;

    @Autowired
    public CaptionController(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    @GetMapping("/{video_id}")
    public ResponseEntity<List<Caption>> getChannel(@PathVariable String videoId) {
    String url = "https://api.vimeo.com/videos/" + videoId + "/texttracks";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + vimeoApiToken);

        ResponseEntity<CaptionResponse> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                new HttpEntity<>(headers),
                CaptionResponse.class
        );

        CaptionResponse captionResponse = response.getBody();
        List<Caption> caption = new ArrayList<>();
        if (captionResponse.getData().size() > 0) {
            caption = captionResponse.getData();
        }

        return ResponseEntity.ok(caption);
    }

}
