package aiss.VimeoMiner.service;

import aiss.vimeominer.model.User.User;
import aiss.vimeominer.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class UserServiceTest {

    @Autowired
    UserService service;

    @Test
    @DisplayName("Test UserService")

    void getUserDetails(){
        User user = service.getUserDetails("7655646");
        System.out.println(user);

    }


    @Test
    @DisplayName("UserService2")

    void getUserComments(){
        User user = service.getUserComment("916", "97");
        System.out.println(user);
        }


}