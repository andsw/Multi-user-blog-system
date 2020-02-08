package cn.jxufe.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;

import cn.jxufe.bean.User;
import cn.jxufe.dao.UserDao;
import cn.jxufe.service.UserService;

/**
 * @author hsw
 * @date 2020/1/12 11:09
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User getUserInfo(Integer userId) {
        if (userId == null) {
            throw new InvalidParameterException("userId is null");
        }
        return userDao.selectByUserId(userId);
    }
}
