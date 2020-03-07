package cn.jxufe.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

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
                .setPassword(cipherPassword).setSalt(salt).setUsername(user.getUsername()));
        } catch (DuplicateKeyException dke) {
            // new user's username or email is in used, now ensure which one is duplicated.
            String rootCause = Objects.requireNonNull(dke.getRootCause()).toString();
            if (rootCause.endsWith("'username'")) {
                throw new RegisterException("此用户名已被注册！");
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
        if ((user.getEmail() == null || "".equals(user.getEmail())
            || user.getEmail().trim().split("@").length != 2)
            && (user.getUsername() == null || user.getUsername().length() < 4)) {
            throw new LoginException("找不到此用户！");
        }
        User userFromDb = userDao.selectByUsernameOrEmail(user);
        if (userFromDb == null) {
            throw new LoginException("用户不存在！");
        } else if (EncodeUtil.generate(user.getPassword(), userFromDb.getSalt()).equals(userFromDb.getPassword())) {
            // 登录成功生成token并存入数据库
            Token token = new Token(userFromDb.getId(),
                EncodeUtil.generateToken(userFromDb.getUsername(), userFromDb.getEmail(), userFromDb.getId()));
            // 不存在则插入，存在则覆盖，新的登录会挤掉旧的登录
            tokenDao.insertToken(token);
            return token;
        } else {
            throw new LoginException("密码错误！");
        }
    }

}
