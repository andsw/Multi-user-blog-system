package cn.jxufe.blog_web.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import cn.jxufe.dao.SubscriptionDao;

/**
 * @author hsw
 * @date 2020/4/12 22:54
 */
@SpringBootTest
public class SubscriptionDaoTest {
    @Autowired
    private SubscriptionDao subscriptionDao;

    @Test
    public void alreadySubscribed() {
        System.out.println(subscriptionDao.alreadySubscribed(1, 2));
        System.out.println(subscriptionDao.alreadySubscribed(1, 10));

    }
}
