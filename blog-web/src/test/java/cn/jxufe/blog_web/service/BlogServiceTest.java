package cn.jxufe.blog_web.service;

import com.github.pagehelper.Page;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import cn.jxufe.bean.Blog;
import cn.jxufe.service.BlogService;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author hsw
 * @date 2020/1/17 22:37
 */
@SpringBootTest
public class BlogServiceTest {
    @Autowired
    private BlogService blogService;

    @Test
    public void testListUserBlogs() {
        Page<Blog> blogPage = (Page<Blog>) blogService.listUserBlogByPagination(1, 1, 10);

        System.out.println(blogPage);
        assertEquals(blogPage.getTotal(), 96);
    }
}
