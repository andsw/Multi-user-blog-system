package cn.jxufe.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.jxufe.bean.User;
import cn.jxufe.dao.UserDao;
import cn.jxufe.service.UserService;

/**
 * @author hsw
 * @date 2020/1/12 11:09
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User getHomepageUser(Integer userId) {
        return userDao.selectByPrimaryKey(userId);
    }

    @Override
    public int updateUserByUserIdSelective(User user) {
        if (user == null) {
            return 0;
        }
        return userDao.updateByPrimaryKeySelective(user);
    }
}
