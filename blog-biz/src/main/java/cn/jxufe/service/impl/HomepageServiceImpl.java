package cn.jxufe.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.InvalidParameterException;

import cn.jxufe.bean.User;
import cn.jxufe.dao.BlogCollectionDao;
import cn.jxufe.dao.BlogDao;
import cn.jxufe.dao.LoveDao;
import cn.jxufe.dao.UserDao;
import cn.jxufe.service.HomepageService;
import cn.jxufe.vo.homepage.HomepageUserVo;

/**
 * @author hsw
 * @date 2020/2/7 10:02 下午
 */
@Service
public class HomepageServiceImpl implements HomepageService {

    private UserDao userDao;
    private BlogDao blogDao;
    private BlogCollectionDao blogCollectionDao;
    private LoveDao loveDao;

    @Autowired
    public HomepageServiceImpl(UserDao userDao, BlogDao blogDao, BlogCollectionDao BlogCollectionDao, LoveDao loveDao) {
        this.userDao = userDao;
        this.blogDao = blogDao;
        this.blogCollectionDao = BlogCollectionDao;
        this.loveDao = loveDao;
    }

    /**
     * 此方法要加上缓存和事务注解
     * 因为有多个查询和数据统计sql，保证其作为一个事务一次提交，
     * 且用缓存尽可能少查询数据库，此数量不必要保证高实时性，每天或每两天更新一次即可！
     * @param userId userId需要判空
     * @return homepageUserVo
     */
    @Transactional
    @Override
    @Cacheable(value = "home_user", key = "#userId")
    public HomepageUserVo getHomepageUserVoByUserId(Integer userId) {
        System.out.println("get from db");
        if (userId == null) {
            throw new InvalidParameterException("userId is null");
        }

        // get user info
        final User user = userDao.selectByUserId(userId);

        // blog view num
        final Integer blogReadNum = blogDao.selectSumReadNumByUserIdLimit(userId, user.getBlogNum());

        // blog collect count by other users
        final Integer collectionNum = blogCollectionDao.selectCountByAuthorId(userId);
        final Integer collectBlogNum = blogCollectionDao.selectCountByUserId(userId);

        // thumb-up count given by other users
        final Integer loveNum = loveDao.selectCountByAuthorId(userId);

        return new HomepageUserVo(user, blogReadNum, loveNum, collectionNum, collectBlogNum);
    }
}
