package aiss.VimeoMiner.service;

import aiss.vimeominer.model.Channel;
import aiss.vimeominer.service.ChannelService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class ChannelServiceTest {
    @Autowired
    ChannelService service;

    @Test
    @DisplayName("Test ChannelService")

    void getChannelDetails(){
        Channel channel = service.getChannelDetails("1234" , "aa9d3c5e651d5df6c3545ae8ecb45329");
        System.out.println(channel);
    }

}