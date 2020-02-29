package cn.jxufe.blog_web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import cn.jxufe.dao.UserDao;

@SpringBootTest
class BlogWebApplicationTests {

    @Autowired
    private UserDao userDao;

    @Value(value = "${cookie.name.token}")
    private String tokenCookieName;

    @Test
    void contextLoads() {
        System.out.println(userDao.selectByUserId(1));
    }

    @Test
    void getValue() {
        System.out.println(tokenCookieName);
    }
}
