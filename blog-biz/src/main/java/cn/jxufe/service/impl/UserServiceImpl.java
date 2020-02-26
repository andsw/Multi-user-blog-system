package cn.jxufe.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Objects;

import cn.jxufe.bean.Token;
import cn.jxufe.bean.User;
import cn.jxufe.dao.TokenDao;
import cn.jxufe.dao.UserDao;
import cn.jxufe.exception.LoginException;
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
    private final TokenDao tokenDao;

    @Autowired
    public UserServiceImpl(UserDao userDao, TokenDao tokenDao) {
        this.userDao = userDao;
        this.tokenDao = tokenDao;
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
    public Token login(User user) throws LoginException {
        // 因为可能是用户名登录，可能是email登录，使用hibernate-validation注解无法表现二者一个可以
        // 为空，不为空的检验，所以直接这里判断。
        if (StringUtils.isEmpty(user.getEmail()) && user.getUsername().length() >=4
        && user.getEmail().trim().split("@").length == 2) {
            throw new LoginException("找不到用户！");
        }
        User userFromDb = userDao.selectByUsernameOrEmail(user);
        if (EncodeUtil.generate(user.getPassword(), userFromDb.getSalt()).equals(userFromDb.getPassword())) {
            // 登录成功生成token并存入数据库
            Token token = new Token(user.getId(),
                EncodeUtil.generateToken(userFromDb.getUsername(), userFromDb.getEmail(), userFromDb.getId()));
            tokenDao.insertToken(token);
            return token;
        } else {
            throw new LoginException("密码错误！");
        }
    }

}
