package cn.jxufe.service;

import cn.jxufe.bean.User;
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

    void registerUser(User user) throws RegisterException;

    User login(User user);
}
