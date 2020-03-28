package cn.jxufe.service;

import cn.jxufe.entity.Token;
import cn.jxufe.entity.User;
import cn.jxufe.entity.vo.user.BasicUserInfo;
import cn.jxufe.exception.LoginException;
import cn.jxufe.exception.RegisterException;

/**
 * @author hsw
 * @date 2020/1/12 11:07
 */
public interface UserService {

    /**
     * 获取主页用户信息
     * @param userId 用户id
     * @return 信息
     */
    User getUserInfo(Integer userId);

    BasicUserInfo getBasicUserInfo(Integer userId);

    void registerUser(User user) throws RegisterException;

    Token login(User user) throws LoginException;
}
