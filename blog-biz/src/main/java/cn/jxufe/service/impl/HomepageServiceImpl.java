package cn.jxufe.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;

import cn.jxufe.bean.User;
import cn.jxufe.dao.BlogDao;
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

    @Autowired
    public HomepageServiceImpl(UserDao userDao, BlogDao blogDao) {
        this.userDao = userDao;
        this.blogDao = blogDao;
    }

    /**
     * 此方法要加上缓存和事务注解
     * 因为有多个查询和数据统计sql，保证其作为一个事务一次提交，
     * 且用缓存尽可能少查询数据库，此数量不必要保证高实时性，每天或每两天更新一次即可！
     * @param userId userId需要判空
     * @return homepageUserVo
     */
    @Override
    public HomepageUserVo getHomepageUserVoByUserId(Integer userId) {

        if (userId == null) {
            throw new InvalidParameterException("userId is null");
        }

        // get user info
        final User user = userDao.selectByUserId(userId);

        // blog view num
        final Integer blogReadNum = blogDao.selectSumReadNumByUserIdLimit(userId, user.getBlogNum());

        // collect num


        return new HomepageUserVo(user, blogReadNum, 0, 0, 0);
    }
}
