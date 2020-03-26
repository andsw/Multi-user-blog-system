package cn.jxufe.dao;

import cn.jxufe.entity.User;

public interface UserDao {

    User selectByUserId(Integer userId);

    User selectByUsernameOrEmail(User user);

    Integer insertNewUser(User user);

    int updateInfoByUserIdSelective(User user);
}