package cn.jxufe.blog_web.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import cn.jxufe.dao.CommentDao;

/**
 * @author hsw
 * @date 2020/4/11 22:36
 */
@SpringBootTest
public class CommentDaoTest {

    @Autowired
    private CommentDao commentDao;
    @Test
    public void selectTest() {
        System.out.println(commentDao.selectFistLoadedCommentByBlogId(1));
    }
}
