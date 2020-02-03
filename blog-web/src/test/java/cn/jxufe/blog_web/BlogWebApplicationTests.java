package cn.jxufe.blog_web;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import cn.jxufe.dao.UserDao;

@SpringBootTest
class BlogWebApplicationTests {

    @Autowired
    private UserDao userDao;

    @Test
    void contextLoads() {
        System.out.println(userDao.selectByPrimaryKey(1));
    }

}
