package cn.jxufe.blog_web.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import cn.jxufe.service.BlogCollectionService;

/**
 * @author hsw
 * @date 2020/4/1 16:27
 */
@SpringBootTest
public class BlogCollectionServiceTest {
    @Autowired
    private BlogCollectionService service;

    @Test
    public void getCollectedBlogByUserIdTest() {
        System.out.println(service.getCollectedByUserId(1));
    }
}
