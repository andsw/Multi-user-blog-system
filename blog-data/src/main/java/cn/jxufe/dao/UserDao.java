package cn.jxufe.dao;

import cn.jxufe.bean.User;

public interface UserDao {

//    customize

    User selectByUserId(Integer userId);

    Integer insertNewUser(User user);

    int deleteByPrimaryKey(Integer id);
}