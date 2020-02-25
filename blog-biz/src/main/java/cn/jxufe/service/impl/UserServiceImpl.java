package cn.jxufe.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.security.InvalidParameterException;
import java.time.Year;
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
        return userDao.selectByUserId(userId);
    }

    @Override
    public void registerUser(User user) throws RegisterException {
        // encode password
        String[] utilResult = EncodeUtil.generate(user.getPassword());
        String cipherPassword = utilResult[0];
        String salt = utilResult[1];
        // insert user
        try {
            userDao.insertNewUser(new User().setEmail(user.getEmail())
                .setPassword(cipherPassword).setSalt(salt).setUsername(user.getSalt()));
        } catch (DuplicateKeyException dke) {
            // new user's username or email is in used, now ensure which one is duplicated.
            String rootCause = Objects.requireNonNull(dke.getRootCause()).toString();
            if (rootCause.endsWith("'username'")) {
                throw new RegisterException("此用户名已被使用！");
            } else {
//                if (rootCause.endsWith("'email'")) {
                throw new RegisterException("此邮箱已被注册！");
            }
        } catch (Exception e) {
            throw new RegisterException("请求超时！");
        }
    }

    @Override
    public User login(User user) {
        // 邮箱有内容，邮箱登录
        if (StringUtils.isEmpty(user.getEmail()) && StringUtils.isEmpty(user.getUsername())) {

        }
        User userFromDb = userDao.selectByUsernameOrEmail(user);

        return null;
    }

}
