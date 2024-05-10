package aiss.VimeoMiner.service;

import aiss.vimeominer.model.Video.Video;
import aiss.vimeominer.service.VideoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
class VideoServiceTest {
    @Autowired
    VideoService service;

    @Test
    @DisplayName("Test VideoService")
    void getVideosChannel(){
        List<Video> videos = service.getVideosChannel("1234");
        System.out.println(videos);
    }

}