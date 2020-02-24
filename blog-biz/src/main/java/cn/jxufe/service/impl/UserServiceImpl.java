package cn.jxufe.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.Objects;

import cn.jxufe.bean.User;
import cn.jxufe.dao.UserDao;
import cn.jxufe.exception.RegisterException;
import cn.jxufe.service.UserService;
import cn.jxufe.util.EncodeUtil;

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

    @Override
    public boolean registerUser(String username, String email, String password) throws RegisterException {
        // encode password
        if (password.length() < 6 ) {
            throw new RegisterException("密码较短！");
        } else if (password.length() > 16) {
            throw new RegisterException("密码过长！");
        }
        String[] utilResult = EncodeUtil.generate(password);
        String cipherPassword = utilResult[0];
        String salt = utilResult[1];
        // insert user
        int result;
        try {
            result = userDao.insertNewUser(new User().setEmail(email)
                .setPassword(cipherPassword).setSalt(salt).setUsername(username));
        } catch (DuplicateKeyException dke) {
            // new user's username or email is in used, now ensure which one is duplicated.
            String rootCause = Objects.requireNonNull(dke.getRootCause()).toString();
            if (rootCause.endsWith("'username'")) {
                throw new RegisterException("此用户名已被使用！");
            } else {
//                if (rootCause.endsWith("'email'")) {
                throw new RegisterException("此邮箱已被注册！");
            }
        }

        //
        return result == 1;
    }

}
