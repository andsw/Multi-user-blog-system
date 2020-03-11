package cn.jxufe.blog_web.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import cn.jxufe.dao.LoveDao;

/**
 * @author hsw
 * @date 2020/3/11 10:52 上午
 */
@SpringBootTest
public class LoveDaoTest {
    @Autowired
    private LoveDao loveDao;
    @Test
    public void testAlreadyThumbUp() {
        System.out.println(loveDao.checkAlreadyThumbUp(1, 1));
        System.out.println(loveDao.checkAlreadyThumbUp(1,2));
    }
}
