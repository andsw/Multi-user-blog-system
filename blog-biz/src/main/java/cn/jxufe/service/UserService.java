package cn.jxufe.service;

import cn.jxufe.bean.User;

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
    User getHomepageUser(Integer userId);

    int updateUserByUserIdSelective(User user);

}
