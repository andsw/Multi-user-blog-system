package cn.jxufe.blog_web.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import cn.jxufe.service.UserService;

/**
 * @author hsw
 * @date 2020/1/17 22:37
 */
@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;
    @Test
    public void testGetUserInfo() {
        System.out.println(userService.getUserInfo(1));
    }
}
