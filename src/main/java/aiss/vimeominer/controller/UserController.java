package aiss.vimeominer.controller;


import aiss.vimeominer.model.User.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController

@RequestMapping("/vimeo/users")
public class UserController {

    @Value("${vimeo.api.token}")
    private String vimeoApiToken;

    private final RestTemplate restTemplate;


    public UserController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("{userId}")

    public ResponseEntity<User> getUser(@PathVariable String userId) {
        String url = "https://api.vimeo.com/users/" + userId;
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + vimeoApiToken);
        ResponseEntity<User> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                new HttpEntity<>(headers),
                User.class
        );

        return ResponseEntity.ok(response.getBody());
    }
}
