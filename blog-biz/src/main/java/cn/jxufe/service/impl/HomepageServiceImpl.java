package cn.jxufe.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.InvalidParameterException;

import cn.jxufe.dao.CorpusDao;
import cn.jxufe.dao.SubscriptionDao;
import cn.jxufe.entity.Corpus;
import cn.jxufe.entity.Do.UserBlogNumDo;
import cn.jxufe.entity.Subscription;
import cn.jxufe.entity.User;
import cn.jxufe.dao.BlogCollectionDao;
import cn.jxufe.dao.BlogDao;
import cn.jxufe.dao.LoveDao;
import cn.jxufe.dao.UserDao;
import cn.jxufe.service.HomepageService;
import cn.jxufe.entity.vo.homepage.HomepageUserVo;

/**
 * @author hsw
 * @date 2020/2/7 10:02 下午
 */
@Service
public class HomepageServiceImpl implements HomepageService {

    private UserDao userDao;
    private BlogDao blogDao;
    private CorpusDao corpusDao;
    private BlogCollectionDao blogCollectionDao;
    private LoveDao loveDao;
    private SubscriptionDao subscriptionDao;

    @Autowired
    public HomepageServiceImpl(UserDao userDao, BlogDao blogDao, CorpusDao corpusDao,
                               BlogCollectionDao BlogCollectionDao, LoveDao loveDao,
                               SubscriptionDao subscriptionDao) {
        this.userDao = userDao;
        this.blogDao = blogDao;
        this.corpusDao = corpusDao;
        this.blogCollectionDao = BlogCollectionDao;
        this.loveDao = loveDao;
        this.subscriptionDao = subscriptionDao;
    }

    /**
     * 此方法要加上缓存和事务注解
     * 因为有多个查询和数据统计sql，保证其作为一个事务一次提交，
     * 且用缓存尽可能少查询数据库，此数量不必要保证高实时性，每天或每两天更新一次即可！
     * TODO : 这里大量使用Dao，考虑后面更改为Service方法，加上业务逻辑
     * @param userId userId需要判空
     * @return homepageUserVo
     */
    @Transactional(readOnly = true)
    @Override
//    @Cacheable(value = "home_user", key = "#userId")
    public HomepageUserVo getHomepageUserVoByUserId(Integer userId) {
        if (userId == null) {
            throw new InvalidParameterException("userId is null");
        }

        // get user info
        final User user = userDao.selectByUserId(userId);

        // 文章数量，字数，阅读数
        UserBlogNumDo numDo = blogDao.selectNumInfoByUserId(userId);

        //文集数量
        Integer corpusNum = corpusDao.selectCorpusCountByUserId(userId);

        // 关注和粉丝数
        Integer subNum = subscriptionDao.selectSubNumByUserId(userId);
        Integer fanNum = subscriptionDao.selectFansNumByUserId(userId);

        Integer collectionNum = blogCollectionDao.selectCountByAuthorId(userId);

        Integer loveNum = loveDao.selectCountByAuthorId(userId);

        final Integer collectBlogNum = blogCollectionDao.selectCountByUserId(userId);

        return new HomepageUserVo(user, numDo.getBlogNum(),
            numDo.getReadNum(), numDo.getWordNum(), corpusNum, subNum, fanNum,
            loveNum, collectionNum, collectBlogNum);
    }
}
