package aiss.VimeoMiner.service;

import aiss.vimeominer.model.Comment.Comment;
import aiss.vimeominer.service.CommentService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class CommentServiceTest {

    @Autowired
    CommentService service;

    @Test
    @DisplayName("Test CommentService")

    void getVideoComments (){
        List<Comment> comments = service.getVideoComments("97");
        assertTrue(!comments.isEmpty(), "The list is empty.");
        System.out.println(comments);

    }

}