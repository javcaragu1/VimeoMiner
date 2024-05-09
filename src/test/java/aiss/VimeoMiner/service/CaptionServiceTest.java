package aiss.VimeoMiner.service;

import aiss.vimeominer.model.Caption;
import aiss.vimeominer.service.CaptionService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest



class CaptionServiceTest {
    @Autowired
    CaptionService service;

    @Test
    @DisplayName("Test CaptionService")


    void getVideoCaption() {
        List<Caption> captions = service.getVideoCaption("937326902");
        assertTrue(!captions.isEmpty(), "The list is empty");
        System.out.println(captions);

    }

}