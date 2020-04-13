package cn.jxufe.blog_web.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import cn.jxufe.dao.UserDao;
import cn.jxufe.entity.User;

/**
 * @author hsw
 * @date 2020/3/11 9:57 上午
 */
@SpringBootTest
public class UserDaoTest {

    @Autowired
    private UserDao userDao;

    @Test
        public void testUpdateNum() {
//        userDao.plusNumByUserIdSelective(1,"sub_num", 1);
    }

    @Test
    public void testUpdateInfo() {
        User user = new User().setId(29).setUsername("hsw1997");
        userDao.updateInfoByUserIdSelective(user);
    }

    @Test
    public void testGetBasicInfo() {
        System.out.println(userDao.selectBasicInfoByUserId(2));
    }
}
